package com.funnygorilla.tinybankaccount.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.funnygorilla.tinybankaccount.common.converter.Converter;
import com.funnygorilla.tinybankaccount.controllers.api.Account;
import com.funnygorilla.tinybankaccount.controllers.api.AccountAPI;
import com.funnygorilla.tinybankaccount.controllers.api.AccountNumber;
import com.funnygorilla.tinybankaccount.dto.AccountDTO;
import com.funnygorilla.tinybankaccount.services.AccountService;

@RestController
@RequestMapping(
	path = "/api/v1/accounts",
	produces = APPLICATION_JSON_UTF8_VALUE)   
public class AccountController implements AccountAPI{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired  
	private AccountService accountService;
	
	@Override
	public ResponseEntity<List<AccountNumber>> getAccounts() {
		
		List <AccountNumber> studentNumbers = new ArrayList<AccountNumber>();
		
		List <AccountDTO> students = this.accountService.getAllAccounts();	
		
		if (students == null || students.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		logger.debug(" --> " + " getAcconts() returned: \n" + students.toString());
		
		// compose response: DTO -> Response
		Iterator<AccountDTO> ite = students.iterator();
		while (ite.hasNext()) {
			studentNumbers.add(Converter.fromStudentDTOToStudentNumber(ite.next()));
		}
		
		return new ResponseEntity<>(studentNumbers, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable(required=true) long id) {
		
		AccountDTO s = this.accountService.getAccountByNumber(id);
		logger.debug(" --> " + " getAccountByID() " + s.toString());
		
		if (s == null || s.getAccountNo() == null){
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		} 
		
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Long> createAccount(@RequestBody @Valid AccountDTO student) {
		
        Long id = this.accountService.createAccount(student);
        logger.debug(" --> " + " Account with " + id+ " created." );
        
        AccountNumber response = new AccountNumber();
        response.setId(id);
        
        return new ResponseEntity<>(id, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") Long id, 
		@Valid @RequestBody Account student) {		
		
    	// check student exists? 
		AccountDTO s = this.accountService.getAccountByNumber(id);	
		if (s == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		// update student otherwise
		s = Converter.fromStudentToStudentDTO(student);		
		s = this.accountService.updateAccount(s);
		
		logger.debug(" --> " + " Account " + s.toString() + " has been updated." );

	    return new ResponseEntity<>(s, HttpStatus.ACCEPTED);		
	}	
	
	@Override
	public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") Long id) {	
	    Boolean result = this.accountService.deleteAccount(id);
        
        if (!result) {
        	logger.debug(" --> " + " Account: " + id + " is NOT FOUNT." );
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.debug(" --> " + " Account: " + id + " has been deleted." );
        
        return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	//----------------------------------------- test Liveness & Readiness probes
    @Autowired
    private  ApplicationEventPublisher eventPublisher;

//    public ExampleController(ApplicationEventPublisher eventPublisher) {
//        this.eventPublisher = eventPublisher;
//    }

    @GetMapping("/complete-normally")
    public String completeNormally() throws Exception {
        return "Hello from Controller";
    }

    @GetMapping("/i-will-sleep-for-30sec")
    public String destroy() throws Exception {
        logger.info("------------------ Sleeping for 30 sec");
        Thread.sleep(30000);
        return "sleep complete";
    }

    @GetMapping("/readiness/accepting")
    public String markReadinesAcceptingTraffic() {
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC);
        return "Readiness marked as ACCEPTING_TRAFFIC";
    }

    @GetMapping("/readiness/refuse")
    public String markReadinesRefusingTraffic() {
        AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.REFUSING_TRAFFIC);
        return "Readiness marked as REFUSING_TRAFFIC";
    }

    @GetMapping("/liveness/correct")
    public String markLivenessCorrect() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.CORRECT);
        return "Liveness marked as CORRECT";
    }

    @GetMapping("/liveness/broken")
    public String markLivenessBroken() {
        AvailabilityChangeEvent.publish(eventPublisher, this, LivenessState.BROKEN);
        return "Liveness marked as BROKEN";
    }	
	
}

package com.example.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.GraphqlService;

import graphql.ExecutionResult;

@RequestMapping("/rest/party")
@RestController
public class PartyResource {

    @Autowired
    private GraphqlService graphql;
    
    @PostMapping  
    public ResponseEntity<Object> get(@RequestBody String query) {
        ExecutionResult result = graphql.getGraphQl().execute(query);
        
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}

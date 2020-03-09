package com.example.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.domain.Contact;
import com.example.domain.Party;
import com.example.repository.PartyCrudRepo;
import com.example.services.datafetcher.AllPartiesDataFetcher;
import com.example.services.datafetcher.PartyDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.errors.SchemaProblem;

@Service
public class GraphqlService {

    @Value("classpath:party.graphql")
    private Resource resource;
    
    private GraphQL graphql ;
    
    @Autowired
    private PartyCrudRepo partyRepo; 

    @Autowired
    private AllPartiesDataFetcher allPartiesDataFetcher;

    @Autowired
    private PartyDataFetcher partyDataFetcher;
    
    
    private void initDataInHSQL() {
        Stream.of(
                new Party.Builder()
                .withName("party1")
                .withAlias("Aliasaa")
               /* .withContacts(Arrays.asList(
                        new Contact.Builder()
                        .withAddressLine1("AddLine1")
                        .withAddressLine2("AddLine2")
                        .withCity("Amsterdam")
                        .build(),
                        new Contact.Builder()
                        .build(),
                        new Contact.Builder()
                        .build()
                 ))*/
                .withStatus("Inactive")
                .build()
                ,
                new Party.Builder()
                .withName("party2")
                .withAlias("Al0e0w9")
                .withStatus("active")
               /* .withContacts(Arrays.asList(
                        new Contact.Builder()
                        .withAddressLine1("AddLine1")
                        .withAddressLine2("AddLine2")
                        .withCity("Amsterdam")
                        .build(),
                        new Contact.Builder()
                        .build(),
                        new Contact.Builder()
                        .build()
                 ))    
                 */            
                .build()
       ).forEach(party -> partyRepo.save(party));
        
    }
    
    @PostConstruct
    private void loadSchema() throws SchemaProblem, IOException {
        
        initDataInHSQL();
        
        //create type def registry
        TypeDefinitionRegistry registeryType = new graphql.schema.idl.SchemaParser().parse(resource.getFile());
        
        
        //create wiring
        
        RuntimeWiring wiring = createRuntimeWiring();
        
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registeryType, wiring);
        
        graphql = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring createRuntimeWiring() {
        
        return   RuntimeWiring
                .newRuntimeWiring()
                .type("Query", 
                     typeWiring-> 
                     typeWiring.dataFetcher("allParties", allPartiesDataFetcher)
                    .dataFetcher("party", partyDataFetcher)                                        
                )
                .build();
                
    }
    
    public GraphQL getGraphQl() {
        return graphql;
    }
}

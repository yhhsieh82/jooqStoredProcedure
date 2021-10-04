package com.example.jooqSp.service;

import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Configuration;
import org.jooq.Results;
import org.jooq.codegen.maven.jooq1.routines.Createcompany;
import org.jooq.codegen.maven.jooq1.routines.Findcompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {
    @Autowired
    @Qualifier(value = "JooqConfiguration")
    private Configuration jooqConfiguration;

    @Autowired
    @Qualifier(value = "JooqConfiguration2")
    private Configuration jooqConfiguration2;


    //    @Transactional
//    @Transactional(transactionManager = "")
    public void getCompany() {

        Findcompany findCompany = new Findcompany();
        findCompany.setMinNumOfEmployee(100L);
        findCompany.setMaxNumOfEmployee(100000L);
        findCompany.execute(jooqConfiguration);

        Results results = findCompany.getResults();

        String totCount = findCompany.getTotCount();
        System.out.printf("sp findCompany OUT: %s%n", totCount);
    }

    @Transactional(transactionManager = "ChainedTransactionalManager", rollbackFor = Exception.class)
    public void createCompany() throws InterruptedException {
        org.jooq.codegen.maven.jooq2.routines.Createcompany createcompany2 = new org.jooq.codegen.maven.jooq2.routines.Createcompany();
        createcompany2.setInName("GFD");
        createcompany2.setInDesc("desc");
        createcompany2.setInIndustry("tech");
        createcompany2.execute(jooqConfiguration2);

        Createcompany createcompany = new Createcompany();
        createcompany.setInName("GOOGOOGO");
        createcompany.setInDesc("desc");
        createcompany.setInIndustry("tech");

        Thread.sleep(4000);
        createcompany.execute(jooqConfiguration);
    }
}

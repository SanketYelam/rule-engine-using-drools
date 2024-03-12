package org.dnyanyog.config;

import java.util.Arrays;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class DroolsBeanFactory {

    private KieServices kieServices = KieServices.Factory.get();

    public KieSession getKieSession() {
    	    	
    	
        KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
        
        
        kieBuilder.buildAll(); 
        
        ReleaseId kieDefaultReleaseId = kieServices.getRepository().getDefaultReleaseId();
     
        KieContainer container = kieServices.newKieContainer(kieDefaultReleaseId);

        return container.newKieSession();
    }
    
    private KieFileSystem getKieFileSystem() {
      KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
      
        List<String> rules = Arrays.asList("org/dnyanyog/rules/discountRules.drl");
        
        for (String rule : rules) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(rule));
        }
        return kieFileSystem;
        
    }
}
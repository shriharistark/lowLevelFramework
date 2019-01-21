package com.lowLevelFrameworkTest.lowLevelFrameworkTest.Controller;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.reflect.TypeToken;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaService;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.Model.Contact;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.Utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    Contact contact;

    @Autowired
    OmegaService datastore;

    @RequestMapping(value = {"/","*"},method = RequestMethod.POST)
    public @ResponseBody String createContact(@RequestBody String requestData){

        Type type = new TypeToken<Map<String,Object>>(){}.getType();
        Map<String, Object> inputData = new Gson().fromJson(requestData,type);
        Map<String, Object> outputData = new LinkedHashMap<>();

        outputData.put("result","fail");
        outputData.put("data","oops. get in touch with support team.");

        String requiredProperties[] = {"active", "firstName"};

        String resultantData = "";

        if(Util.hasRequiredProperties(inputData,requiredProperties)){

            try {
                contact.setFirstName(inputData.get("firstName").toString());
                contact.setLastName(inputData.get("lastName").toString());
                contact.setActive(inputData.get("active").toString().toUpperCase().equals("TRUE"));
                contact.setAge((int)Integer.parseInt(inputData.get("age").toString().split("\\.")[0]));

                Key returnKey = datastore.persist(contact);

                outputData.put("result", "success");
                inputData.put("key", returnKey.toString());
                outputData.put("data", new Gson().toJson(inputData));
            }catch (Exception k){
                k.printStackTrace();
                outputData.put("result","fail");
                outputData.put("data","something went wrong on our side.");
            }

        }else {
            for(String k : requiredProperties){
                resultantData = resultantData.concat(k).concat(", ");
            }
            outputData.put("data",resultantData.concat(" are mandatory properties"));
        }

        return new Gson().toJson(outputData);
    }

    @RequestMapping(value = {"/","*"},method = RequestMethod.GET)
    public @ResponseBody String getContact(@RequestParam String id){

        Type type = new TypeToken<Map<String,Object>>(){}.getType();
        Map<String, Object> outputData = new LinkedHashMap<>();

        outputData.put("result","fail");
        outputData.put("data","oops. get in touch with support team.");

        String resultantData = "";

        if(id != null && !id.isEmpty()){

            try {


                Contact contact = (Contact) datastore.get(id,Contact.class);

                Map<String, Object> contactMap = new LinkedHashMap<>();
                contactMap.put("id",contact.getID());
                contactMap.put("age",contact.getAge());
                contactMap.put("active",contact.isActive());
                contactMap.put("firstName",contact.getFirstName());
                contactMap.put("lastName",contact.getLastName());

                outputData.put("result", "success");
                outputData.put("data", new Gson().toJson(contactMap));

            }catch (Exception k){
                k.printStackTrace();
                outputData.put("result","fail");
                outputData.put("data","something went wrong on our side.");
            }

        }else {
            resultantData = "contact ID";
            outputData.put("data",resultantData.concat(" are mandatory properties"));
        }

        return new Gson().toJson(outputData);
    }
}

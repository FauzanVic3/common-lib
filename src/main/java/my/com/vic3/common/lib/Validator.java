/*
 *  This software is the confidential and proprietary information
 *  of Telekom Research & Development Sdn. Bhd.
 */
package my.com.vic3.common.lib;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Fauzan
 */
public class Validator extends JsonProperty{

    
    public static String MSG_NULL_EMPTY = "Object is null or empty";
    public static String MSG_INTEGER = "Invalid integer";
    public static String MSG_NUMBER = "Invalid number";
    public static String MSG_DATE = "Invalid date";
    public static String MSG_DATETIME = "Invalid datetime";

    private String requestId;
    private String errorMessage = "";
    
    private List<FieldMessage> fieldMessageList = new ArrayList<FieldMessage>();

    public Validator(){     
    }
        
    public Validator(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addFailure(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public List<FieldMessage> getFieldMessageList() {
        return fieldMessageList;
    }

    public boolean validate() {
        if (this.errorMessage.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}

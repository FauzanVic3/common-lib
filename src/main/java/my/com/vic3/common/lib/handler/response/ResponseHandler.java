/*
 * Copyright (C) 2023 Fauzan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package my.com.vic3.common.lib.handler.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import my.com.vic3.common.lib.APIConstant;
import my.com.vic3.common.lib.ExceptionResponse;
import my.com.vic3.common.lib.ModelResponse;
import my.com.vic3.common.lib.ResponseHeader;
import my.com.vic3.common.lib.handler.exception.BadRequestException;
import my.com.vic3.common.lib.handler.exception.NotFoundException;
import my.com.vic3.common.lib.handler.exception.SystemException;
import my.com.vic3.common.lib.handler.exception.UnauthorizedException;
import my.com.vic3.common.lib.handler.exception.UnsupportedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Fauzan
 * 
 * This class is the main ResponseEntity handler for standardized return response
 * 
 */
public class ResponseHandler {

    public String fetchResponseTime() {
        String time = new SimpleDateFormat(APIConstant.DATE_TIME_PATTERN).format(new Date());
        return time;
    }

    String requestId = null;

    public ResponseHandler() {

    }

    public ResponseHandler(String requestId) {
        this.requestId = requestId;
    }

    public ResponseHeader generateOkHeader(String message) {

        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setCode(String.valueOf(HttpStatus.OK.value()));
        responseHeader.setMessage(message);
        responseHeader.setRequestId(requestId);
        responseHeader.setResponseDateTime(fetchResponseTime());
        return responseHeader;
    }
    
    public ResponseEntity responseOk(ModelResponse modelResponse) {
        if (modelResponse.getResponseHeader() == null) {
            modelResponse.setResponseHeader(generateOkHeader(HttpStatus.OK.name()));
        }

        return ResponseEntity.ok().body(modelResponse);
                
    }
    
    public ResponseEntity responseOk(ModelResponse modelResponse, String requestId) {
        if (modelResponse.getResponseHeader() == null) {
            modelResponse.setResponseHeader(generateOkHeader(HttpStatus.OK.name()));
        }

        return ResponseEntity.ok().body(modelResponse);
                
    }

    public ResponseEntity responseOkNoHeader(ModelResponse modelResponse) {
        return ResponseEntity.ok().body(modelResponse);
    }
        
    public ExceptionResponse generateExceptionResponse(String code, String message, String errorMessage){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        
        exceptionResponse.setResponseHeader(new ResponseHeader());
        exceptionResponse.getResponseHeader().setCode(code);
        exceptionResponse.getResponseHeader().setMessage(message);
        exceptionResponse.getResponseHeader().setResponseDateTime(new ResponseHandler().fetchResponseTime());
        if (errorMessage != null) {
            exceptionResponse.setErrorMessage(errorMessage);
        }
        
        return exceptionResponse;
    }
    
    public ResponseEntity throwNotFoundExceptionResponse(String exception){
        throw new NotFoundException(exception);
    }
    
    public ResponseEntity throwBadRequestExceptionResponse(String exception){
        throw new BadRequestException(exception);
    }
    
    public ResponseEntity throwUnauthorizedExceptionResponse(String exception){
        throw new UnauthorizedException(exception);
    }
    
    public ResponseEntity throwUnsupportedTypeExceptionResponse(String exception){
        throw new UnsupportedTypeException(exception);
    }
    
    public ResponseEntity throwSystemExceptionResponse(String exception){
        throw new SystemException(exception);
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

}

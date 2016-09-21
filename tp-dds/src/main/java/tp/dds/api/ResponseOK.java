package tp.dds.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseOK  {

    @JsonProperty("status")
    private String status;


    @JsonCreator
    public ResponseOK( String status){
        this.status=status;
    }

}

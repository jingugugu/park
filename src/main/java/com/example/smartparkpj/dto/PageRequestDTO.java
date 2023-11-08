package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value =1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String link;
    private String type;
    private String keyword;

    private Long bno;

    public int getSkip(){
        return (page - 1) * size;
    }

    public String getLink(){
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if(this.type != null)
                builder.append("type=" + type);


            if(this.keyword != null){
                try{
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch(UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
            return builder.toString();
    }
}

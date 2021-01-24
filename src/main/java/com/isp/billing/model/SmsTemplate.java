package com.isp.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Iqbal on 7/25/2019.
 */

@Entity
@Table(name = "Sms_Template")
//@Getter @Setter @NoArgsConstructor
@Data
public class SmsTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Template_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "Msg")
    private String msg;

    @Column(name = "STATUS")
    private Boolean status;



    @JsonIgnore
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "createdBy", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
            @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATED_BY")),
            @AttributeOverride(name = "lastUpdateLogin", column = @Column(name = "LAST_UPDATED_LOGIN")),
            @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE"))})
    private CommonColumn column;


}

package com.fatihctn.pigeon.entity.embeddedable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Getter @Setter
public class CreateUpdateDeleteTimestamp extends CreateUpdateTimestamp {
    @Column(name = "deletedAt")
    private Date deletedAt;
}

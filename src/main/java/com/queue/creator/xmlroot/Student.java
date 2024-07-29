package com.queue.creator.xmlroot;

import lombok.Data;
import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="stdId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="stdName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="stdAddr" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stdId",
    "stdName",
    "stdAddr"
})
@XmlRootElement(name = "student")
@Data
public class Student implements Serializable {

    /**
     * -- GETTER --
     *  Gets the value of the stdId property.
     * -- SETTER --
     *  Sets the value of the stdId property.

     */
    protected int stdId;
    /**
     * -- GETTER --
     *  Gets the value of the stdName property.
     *
     *
     * -- SETTER --
     *  Sets the value of the stdName property.
     *
     @return
     *     possible object is
     *     {@link String }
      * @param value
      *     allowed object is
      *     {@link String }
     */
    @XmlElement(required = true)
    protected String stdName;
    /**
     * -- GETTER --
     *  Gets the value of the stdAddr property.
     *
     *
     * -- SETTER --
     *  Sets the value of the stdAddr property.
     *
     @return
     *     possible object is
     *     {@link String }
      * @param value
      *     allowed object is
      *     {@link String }
     */
    @XmlElement(required = true)
    protected String stdAddr;

}

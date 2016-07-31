//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.31 at 11:27:01 PM BST 
//


package pl.baczkowicz.spy.common.generated;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Task complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Task"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="autoStart" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="repeat" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Task")
@XmlSeeAlso({
    ScriptDetails.class
})
public class Task implements Serializable, Cloneable, CopyTo, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "autoStart")
    protected Boolean autoStart;
    @XmlAttribute(name = "repeat")
    protected Boolean repeat;

    /**
     * Default no-arg constructor
     * 
     */
    public Task() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public Task(final Boolean autoStart, final Boolean repeat) {
        this.autoStart = autoStart;
        this.repeat = repeat;
    }

    /**
     * Gets the value of the autoStart property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoStart() {
        return autoStart;
    }

    /**
     * Sets the value of the autoStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoStart(Boolean value) {
        this.autoStart = value;
    }

    /**
     * Gets the value of the repeat property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRepeat() {
        return repeat;
    }

    /**
     * Sets the value of the repeat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRepeat(Boolean value) {
        this.repeat = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            Boolean theAutoStart;
            theAutoStart = this.isAutoStart();
            strategy.appendField(locator, this, "autoStart", buffer, theAutoStart);
        }
        {
            Boolean theRepeat;
            theRepeat = this.isRepeat();
            strategy.appendField(locator, this, "repeat", buffer, theRepeat);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Task)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Task that = ((Task) object);
        {
            Boolean lhsAutoStart;
            lhsAutoStart = this.isAutoStart();
            Boolean rhsAutoStart;
            rhsAutoStart = that.isAutoStart();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "autoStart", lhsAutoStart), LocatorUtils.property(thatLocator, "autoStart", rhsAutoStart), lhsAutoStart, rhsAutoStart)) {
                return false;
            }
        }
        {
            Boolean lhsRepeat;
            lhsRepeat = this.isRepeat();
            Boolean rhsRepeat;
            rhsRepeat = that.isRepeat();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "repeat", lhsRepeat), LocatorUtils.property(thatLocator, "repeat", rhsRepeat), lhsRepeat, rhsRepeat)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            Boolean theAutoStart;
            theAutoStart = this.isAutoStart();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "autoStart", theAutoStart), currentHashCode, theAutoStart);
        }
        {
            Boolean theRepeat;
            theRepeat = this.isRepeat();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "repeat", theRepeat), currentHashCode, theRepeat);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof Task) {
            final Task copy = ((Task) draftCopy);
            if (this.autoStart!= null) {
                Boolean sourceAutoStart;
                sourceAutoStart = this.isAutoStart();
                Boolean copyAutoStart = ((Boolean) strategy.copy(LocatorUtils.property(locator, "autoStart", sourceAutoStart), sourceAutoStart));
                copy.setAutoStart(copyAutoStart);
            } else {
                copy.autoStart = null;
            }
            if (this.repeat!= null) {
                Boolean sourceRepeat;
                sourceRepeat = this.isRepeat();
                Boolean copyRepeat = ((Boolean) strategy.copy(LocatorUtils.property(locator, "repeat", sourceRepeat), sourceRepeat));
                copy.setRepeat(copyRepeat);
            } else {
                copy.repeat = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new Task();
    }

}

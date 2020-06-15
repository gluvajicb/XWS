//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.15 at 08:48:41 PM CEST 
//


package rs.ac.uns.xmltim.process;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="article_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="author_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviews">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="review_element" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="review_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="reviewer_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "articleId",
    "authorId",
    "reviews"
})
@XmlRootElement(name = "process")
public class Process {

    @XmlElement(name = "article_id", required = true)
    protected String articleId;
    @XmlElement(name = "author_id", required = true)
    protected String authorId;
    @XmlElement(required = true)
    protected Process.Reviews reviews;
    @XmlAttribute(name = "ID")
    protected String id;

    /**
     * Gets the value of the articleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * Sets the value of the articleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleId(String value) {
        this.articleId = value;
    }

    /**
     * Gets the value of the authorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorId(String value) {
        this.authorId = value;
    }

    /**
     * Gets the value of the reviews property.
     * 
     * @return
     *     possible object is
     *     {@link Process.Reviews }
     *     
     */
    public Process.Reviews getReviews() {
        return reviews;
    }

    /**
     * Sets the value of the reviews property.
     * 
     * @param value
     *     allowed object is
     *     {@link Process.Reviews }
     *     
     */
    public void setReviews(Process.Reviews value) {
        this.reviews = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="review_element" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="review_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="reviewer_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "reviewElement"
    })
    public static class Reviews {

        @XmlElement(name = "review_element")
        protected List<Process.Reviews.ReviewElement> reviewElement;

        /**
         * Gets the value of the reviewElement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reviewElement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReviewElement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Process.Reviews.ReviewElement }
         * 
         * 
         */
        public List<Process.Reviews.ReviewElement> getReviewElement() {
            if (reviewElement == null) {
                reviewElement = new ArrayList<Process.Reviews.ReviewElement>();
            }
            return this.reviewElement;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="review_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="reviewer_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "reviewId",
            "reviewerId"
        })
        public static class ReviewElement {

            @XmlElement(name = "review_id", required = true)
            protected String reviewId;
            @XmlElement(name = "reviewer_id", required = true)
            protected String reviewerId;

            /**
             * Gets the value of the reviewId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReviewId() {
                return reviewId;
            }

            /**
             * Sets the value of the reviewId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReviewId(String value) {
                this.reviewId = value;
            }

            /**
             * Gets the value of the reviewerId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReviewerId() {
                return reviewerId;
            }

            /**
             * Sets the value of the reviewerId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReviewerId(String value) {
                this.reviewerId = value;
            }

        }

    }

}

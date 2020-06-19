<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:ns1="http://www.uns.ac.rs/XMLTim/Article" 
  xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
  
  <xsl:template match="/">
    <fo:root>
      <fo:layout-master-set>
        <fo:simple-page-master master-name="bookstore-page">
          <fo:region-body margin="0.75in"/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      
      <fo:page-sequence master-reference="bookstore-page">
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px">
            <xsl:value-of select="count(ns1:article/ns1:title)"/>
            <xsl:value-of select="count(ns1:ID)"/>
          </fo:block>
          
          <fo:block font-family="sans-serif" font-size="20px" font-weight="bold" padding="10px">
            Available books:
          </fo:block>
          <fo:block text-indent="10px">
            Highlighting (*) the book titles with a  
            <fo:inline font-weight="bold">price less than $40</fo:inline>.
          </fo:block>
          
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>
</xsl:stylesheet>
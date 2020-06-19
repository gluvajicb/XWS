<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns1="http://www.uns.ac.rs/XMLtim" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/ns1:coverLetter">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block text-align="center" font-family="sans-serif" font-size="24px" font-weight="bold" padding="10px">
                        <xsl:value-of select="ns1:title" />
                    </fo:block>
                    <fo:block text-align="center">
                        <xsl:text>Date of submission: </xsl:text>
                        <xsl:value-of select="ns1:submission_date" />
                    </fo:block>

                    <fo:block>
                        <xsl:value-of select="ns1:text" />
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>

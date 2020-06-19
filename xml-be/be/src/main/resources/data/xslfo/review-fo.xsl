<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns1="http://www.uns.ac.rs/XMLtim/Review" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/ns1:review">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block text-align="center" font-family="sans-serif" font-size="18px" font-weight="bold" padding="10px">
                        <xsl:text>Review for article ID:</xsl:text>
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns1:article_id" />
                        </fo:inline>
                    </fo:block>

                    <fo:block>
                        <xsl:text>Overall:</xsl:text>
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns1:overall_recommendation" />
                        </fo:inline>
                    </fo:block>

                    <fo:block>
                        <xsl:text>Questionnaire:</xsl:text>
                        <xsl:for-each select="ns1:questionnaire/ns1:question_element">
                            <fo:block>
                                <xsl:text>Question:</xsl:text>
                                <fo:block font-weight="bold">
                                    <xsl:value-of select="ns1:question" />
                                </fo:block>
                                <xsl:text>Response:</xsl:text>
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="ns1:response" />
                                </fo:inline>
                                <xsl:text>),  </xsl:text>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>

                    <fo:block>
                        <xsl:text>Comments:</xsl:text>
                        <fo:block font-weight="bold">
                            <xsl:value-of select="ns1:comments" />
                        </fo:block>
                    </fo:block>

                    <fo:block>
                        <xsl:text>Confidential comments:</xsl:text>
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns1:confidental-comments" />
                        </fo:inline>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>

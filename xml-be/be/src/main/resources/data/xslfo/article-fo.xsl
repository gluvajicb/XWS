<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns1="http://www.uns.ac.rs/XMLtim/Article" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/ns1:article">
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
                        <xsl:text>Status: </xsl:text>
                        <xsl:value-of select="ns1:status" />
                    </fo:block>
                    <fo:block space-before="30px">
                        Published by:
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="ns1:publisher" />
                        </fo:inline>
                    </fo:block>
                    <fo:block>
                        Written by:
                        <xsl:for-each select="ns1:authors/ns1:author">
                            <fo:inline>
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="ns1:name" />
                                </fo:inline>
                                <xsl:text> </xsl:text>
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="ns1:surname" />
                                </fo:inline>
                                <xsl:text> (</xsl:text>
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="ns1:university" />
                                </fo:inline>
                                <xsl:text>),  </xsl:text>
                            </fo:inline>
                        </xsl:for-each>
                    </fo:block>

                    <xsl:for-each select="ns1:abstract/ns1:paragraph">
                        <fo:block space-before="20px" text-align="center">
                            <xsl:for-each select="ns1:text">
                                <fo:block>
                                    <xsl:value-of select="." />
                                </fo:block>
                            </xsl:for-each>
                        </fo:block>
                    </xsl:for-each>

                    <xsl:for-each select="ns1:abstract/ns1:keywords">
                        <fo:block space-before="20px">
                            <xsl:for-each select="ns1:keyword">
                                <fo:inline>
                                    <xsl:value-of select="." />
                                </fo:inline>
                                <xsl:text>, </xsl:text>
                            </xsl:for-each>
                        </fo:block>
                    </xsl:for-each>

                    <xsl:for-each select="ns1:sections/ns1:section">
                        <fo:block space-before="50px">
                            <fo:block text-align="center" font-family="sans-serif" font-size="20px" font-weight="bold" padding="10px">
                                <xsl:value-of select="ns1:title" />
                            </fo:block>

                            <xsl:for-each select="ns1:paragraph">
                                <fo:block space-before="20px">
                                    <xsl:for-each select="ns1:text">
                                        <fo:block>
                                            <xsl:value-of select="." />
                                        </fo:block>
                                    </xsl:for-each>
                                </fo:block>
                            </xsl:for-each>
                        </fo:block>
                    </xsl:for-each>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>

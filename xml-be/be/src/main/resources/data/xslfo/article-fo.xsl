<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:ns1="http://www.uns.ac.rs/XmlTim"
        version="2.0">
  <xsl:template match="/ns1:Article">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="article-page"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-left="2.5cm"
                        margin-right="2.5cm">
                    <fo:region-body
                            margin-top="1cm"
                            margin-bottom="2cm"/>
                    <fo:region-after
                            region-name="article-page-footer"
                            extent="2cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="article-page">
                <fo:static-content flow-name="article-page-footer">
                    <fo:block>
                        <fo:table table-layout="fixed" width="100%" border-spacing="1pt">
                            <fo:table-column column-width="proportional-column-width(1)"/>
                            <fo:table-column column-width="150"/>
                            <fo:table-body start-indent="0pt">
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" padding="0" height="25" display-align="center">
                                        <fo:block/>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" padding="0" display-align="center">
                                        <fo:block>
                                            <fo:block text-align="center">
                                                <fo:leader leader-pattern="rule" top="-37pt" rule-thickness="1" leader-length="100%" color="black"/>
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="smaller" padding="0" text-align="left" display-align="center">
                                        <fo:block/>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="smaller" padding="0" text-align="right" display-align="center">
                                        <fo:block>
                                            <fo:inline font-weight="bold">Page: </fo:inline>
                                            <fo:page-number font-weight="bold"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Baskerville">
                      <xsl:apply-templates select="ns1:Title"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

  <xsl:template match="ns1:Body">
    <xsl:apply-templates select="ns1:Title"/>
      
    </xsl:template>

    <xsl:template match="xm:Title">
        <fo:block font-size="26px" font-weight="500" text-align="center" margin-bottom="20px">
            <xsl:value-of select="."/>
        </fo:block>
    </xsl:template>

    <xsl:template match="xm:Info">
        <xsl:apply-templates select="xm:Authors"/>
    </xsl:template>

    <xsl:template match="xm:Authors">
        <fo:block text-align="center">
            <xsl:for-each select="xm:AuthorArticle">
                <fo:block space-after="12px">
                    <fo:block>
                        <fo:inline>
                            <xsl:value-of select="xm:Name"/>
                            <xsl:text>,  </xsl:text>
                            <xsl:value-of select="xm:Email"/>
                            <xsl:text>,  </xsl:text>
                            <xsl:value-of select="xm:PhoneNumber"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block>
                        <fo:inline>
                            <xsl:value-of select="xm:Institution/xm:Name"/>
                            <xsl:text>,  </xsl:text>
                            <xsl:value-of select="xm:Institution/xm:City"/>
                            <xsl:text>,  </xsl:text>
                            <xsl:value-of select="xm:Institution/xm:Country"/>
                        </fo:inline>
                    </fo:block>
                </fo:block>
            </xsl:for-each>
        </fo:block>
    </xsl:template>

    <xsl:template match="xm:Abstract">
        <fo:block font-size="16px" font-weight="600" space-after="5px">
            Abstract
        </fo:block>
        <fo:block font-weight="600">
            <xsl:apply-templates select="xm:Paragraph"/>
        </fo:block>
        <fo:block margin-left="15px" space-after="5px" space-before="15px" font-weight="600">
            <fo:inline font-size="12px">
                Keywords --
                <xsl:value-of select="xm:Keywords"/>
            </fo:inline>
        </fo:block>
    </xsl:template>

    <xsl:template match="xm:Paragraph">
        <fo:block text-align="justify" text-indent="10px">
            <xsl:apply-templates />
        </fo:block>
    </xsl:template>

    <xsl:template match="xm:Paragraph/text()">
        <xsl:value-of select="."/>
    </xsl:template>

    <xsl:template match="xm:Italic">
        <fo:inline font-style="italic">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="xm:Bold">
        <fo:inline font-weight="bold">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="xm:Underline">
        <fo:inline text-decoration="underline">
            <xsl:apply-templates/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="xm:Quote">
        <fo:inline>
            "<xsl:apply-templates select="xm:Text"/>"
            <xsl:apply-templates select="xm:Author"/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="xm:Text">
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="xm:Text/text()">
        <xsl:value-of select="."/>
    </xsl:template>

    <xsl:template match="xm:Author">
        <fo:inline font-style="italic">
            - <xsl:value-of select="text()"/>
        </fo:inline>
    </xsl:template>


    <xsl:template match="xm:ReferenceLink">
        <xsl:variable name="referenceID" select="."/>
        <xsl:variable name="number" select="count(//xm:Reference[@ID =$referenceID]/preceding-sibling::xm:Reference)+1"/>
        <fo:basic-link color="blue" internal-destination="{$referenceID}">
            [
            <fo:inline text-decoration="underline">
                Ref <xsl:value-of select="$number"/>
            </fo:inline>
            ]
        </fo:basic-link>
    </xsl:template>

    <xsl:template match="xm:FigureLink">
        <xsl:variable name="figureID" select="."/>
        <xsl:variable name="number" select="count(//xm:Figure[@ID = $figureID]/preceding::xm:Figure)+1"/>
        <fo:basic-link color="blue" internal-destination="{$figureID}">
            [
            <fo:inline text-decoration="underline">
                Fig <xsl:value-of select="$number"/>
            </fo:inline>
            ]
        </fo:basic-link>
    </xsl:template>

    <xsl:template match="xm:Content">
        <xsl:apply-templates select="xm:Section">
            <xsl:with-param name="sectionNumbering" select="''"/>
        </xsl:apply-templates>
    </xsl:template>

    <xsl:template match="xm:Section">
        <xsl:param name="sectionNumbering"/>
        <fo:block space-before="15px">
            <xsl:variable name="id" select="@ID"/>
            <xsl:choose>
                <xsl:when test="''=$sectionNumbering">
                    <xsl:apply-templates>
                        <xsl:with-param name="sectionNumbering" select="string(count(//xm:Section[@ID = $id]/preceding-sibling::xm:Section) + 1)"/>
                    </xsl:apply-templates>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates>
                        <xsl:with-param name="sectionNumbering" select="concat($sectionNumbering,concat('.', string(count(//xm:Section[@ID = $id]/preceding-sibling::xm:Section) + 1)))"/>
                    </xsl:apply-templates>
                </xsl:otherwise>
            </xsl:choose>
        </fo:block>
    </xsl:template>

    <xsl:template match="xm:Section/xm:Title">
        <xsl:param name="sectionNumbering"/>
        <fo:block font-size="16px" font-weight="500" space-after="5px">
            <xsl:choose>
                <xsl:when test="contains($sectionNumbering, '.')">
                    <fo:block font-size="14px" font-style="italic">
                        <xsl:value-of select="concat($sectionNumbering, ' ')"/> <xsl:value-of select="text()"/>
                    </fo:block>
                </xsl:when>
                <xsl:otherwise>
                    <fo:block font-size="16px" text-align="center">
                        <xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyz'" />
                        <xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
                        <xsl:value-of select="concat($sectionNumbering, ' ')"/> <xsl:value-of select="translate(text(), $smallcase, $uppercase)"/>
                    </fo:block>
                </xsl:otherwise>
            </xsl:choose>
        </fo:block>
    </xsl:template>

    <xsl:template match="xm:SectionContent">
        <xsl:param name="sectionNumbering"/>
        <xsl:apply-templates>
            <xsl:with-param name="sectionNumbering" select="$sectionNumbering"/>
        </xsl:apply-templates>
    </xsl:template>

    <xsl:template match="xm:List">
        <fo:block space-before="8px" space-after="8px">
            <fo:list-block >
                <xsl:for-each select="xm:ListItem">
                    <fo:list-item>
                        <fo:list-item-label end-indent="label-end()" text-align="right">
                            <fo:block font-family="Courier">•</fo:block>
                        </fo:list-item-label>
                        <fo:list-item-body start-indent="body-start()">
                            <fo:block>
                                <fo:inline>
                                    <fo:inline font-weight="500">
                                        <xsl:value-of select="text()"/>
                                    </fo:inline>
                                </fo:inline>
                            </fo:block>
                        </fo:list-item-body>
                    </fo:list-item>
                </xsl:for-each>
            </fo:list-block>
        </fo:block>
    </xsl:template>

    <xsl:template match="xm:Figure">
        <xsl:variable name="id" select="@ID"/>
        <xsl:variable name="number" select="count(//xm:Figure[@ID = $id]/preceding::xm:Figure)+1"/>
        <fo:block space-before="12px" space-after="12px">
            <fo:table>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block text-align="center">
                                <fo:external-graphic content-height="scale-to-fit" content-width="250px" scaling="non-uniform">
                                    <xsl:attribute name="src">
                                        data:image/*;base64,<xsl:value-of select="xm:Binary"/>
                                    </xsl:attribute>
                                    <xsl:attribute name="id">
                                        <xsl:value-of select="@ID"/>
                                    </xsl:attribute>
                                </fo:external-graphic>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block text-align="center" font-weight="bold">
                                <fo:inline>
                                    Figure[<xsl:value-of select="$number"/>]: <xsl:value-of select="xm:Title"/>
                                </fo:inline>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>


    <xsl:template match="xm:References">
        <fo:block>
            <fo:block font-size="16px" font-weight="500" space-before="20px" space-after="5px">
                References
            </fo:block>
            <fo:block>
                <xsl:for-each select="xm:Reference">
                    <fo:block>
                        <fo:inline>
                            <xsl:variable name="i" select="position()"/>
                            [<xsl:value-of select="$i" />]
                            <fo:inline font-style="italic">
                                <xsl:attribute name="id">
                                    <xsl:value-of select="@ID"/>
                                </xsl:attribute>
                                <xsl:for-each select="xm:AuthorsRef/xm:Author">
                                    <xsl:value-of select="text()"/>
                                    <xsl:choose>
                                        <xsl:when test="position() != last()">, </xsl:when>
                                    </xsl:choose>
                                </xsl:for-each>
                                (<xsl:value-of select="xm:Year"/>).
                                <xsl:choose>
                                    <xsl:when test="xm:Link">
                                        <xsl:variable name="linkvar" select="concat('http://localhost:4200/article/', xm:Link)"/>
                                        <fo:basic-link color="blue" external-destination="{$linkvar}">
                                            <fo:inline text-decoration="underline">
                                                <xsl:value-of select="xm:Title"/>
                                            </fo:inline>
                                        </fo:basic-link>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="xm:Title"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <xsl:value-of select="xm:Title"/>
                                Journal: <xsl:value-of select="xm:Journal/xm:Name"/>
                            </fo:inline>
                        </fo:inline>
                    </fo:block>
                </xsl:for-each>
            </fo:block>
        </fo:block>
    </xsl:template>






</xsl:stylesheet>

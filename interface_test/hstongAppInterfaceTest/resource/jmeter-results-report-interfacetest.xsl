<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="no" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN" doctype-system="http://www.w3.org/TR/html4/loose.dtd"/>
    <xsl:strip-space elements="*"/>

    <xsl:param    name="titleReport" select="'HStong Interface Test Results'"/>
    <xsl:param    name="dateReport" select="'date not defined'"/>
    <xsl:template match="/testResults">
        <html lang="en">
            <head>
                <meta name="Author" content="shanhe.me"/>
                <title>JMeter Test Results</title>
                <style type="text/css"><![CDATA[
            
                * { margin: 0; padding: 0 }
                html, body { width: 100%; height: 100%; background: #b4b4b4; font-size: 12px }
                table { border: none; border-collapse: collapse; table-layout: fixed }
                td { vertical-align: baseline; font-size: 12px }
                #left-panel { position: absolute; left: 0; top: 0; bottom: 0; width: 400px; overflow: auto; background: #dee4ea }
                #left-panel li.navigation { font-weight: bold; cursor: default; color: #9da8b2; line-height: 18px; background-position: 0px 0px; background-repeat: no-repeat;}
                #left-panel li.navigation_parent { font-weight: bold; cursor: default; color: hsla(210, 29%, 13%, 0.97); line-height: 18px; background-position: 8px 5px; background-repeat: no-repeat; padding: 0 0 0 1px;background-size:11px 11px;position: relative;
    margin-left: 10px; }                
				#left-panel li.success { color: #565b60 }
                #left-panel li.failure { color: red }
                #left-panel li { list-style: none; color: black; cursor: pointer }
                #left-panel li.selected { background-repeat: repeat-x; color: white; background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAUCAYAAABMDlehAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sDEBQxLTs5O2gAAAAdaVRYdENvbW1lbnQAAAAAAENyZWF0ZWQgd2l0aCBHSU1QZC5lBwAAAEdJREFUCNc1y7ERgEAMA0GNUhIyGqM2uqKgtyWZhE9v53A/7/A6D7BkMDNgy2AroB2wHTCZv5UMOgFLG1bvd7XBckBlwCXjA5wMOF5iOX/MAAAAAElFTkSuQmCC) }
                #result-list > ul >li.parentSelected {background-repeat: repeat-x; color: white; background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAUCAYAAABMDlehAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sDEBQxLTs5O2gAAAAdaVRYdENvbW1lbnQAAAAAAENyZWF0ZWQgd2l0aCBHSU1QZC5lBwAAAEdJREFUCNc1y7ERgEAMA0GNUhIyGqM2uqKgtyWZhE9v53A/7/A6D7BkMDNgy2AroB2wHTCZv5UMOgFLG1bvd7XBckBlwCXjA5wMOF5iOX/MAAAAAElFTkSuQmCC) }
				#left-panel div { line-height: 20px; background-position: 25px 3px; background-repeat: no-repeat; padding: 0 0 0 45px }
                #left-panel div.success { background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAOCAYAAADwikbvAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sDEBULEEc6wzcAAAAdaVRYdENvbW1lbnQAAAAAAENyZWF0ZWQgd2l0aCBHSU1QZC5lBwAAAiNJREFUKM99kktIVGEYhp/jzJl08lI6logp2Y2EFkbtaqlFROsWrlq4ioJWQRs37VoUVItWkYEVRGSBlhleCpywDEWxTEuxcURTZ6YzxzP/5WshCOHUt36f93kXnyMi5Lsnb4clI4s4fhkXzp5w8mWcfHBvfEpUxVdCUUU6lUPNHuD86cYtBQX5GhPrM7hRg7GaSDRg2vuUd90WuOPVsOyqy6FFo2yOQHlU1S9z9dZT+S/8I7GCLlkAN4eyAf56mnT6Fy1HLnGuuYa++MS/4e74qMRqfXLaJ9BpfnsrLC0m2BYuoqwUbj/+274JD43OEqmexwvW8NUKXnaZtVSS1pNtAAyOvyC6v48HnUNb4Z7PH8UtTlIQWA5tb2RhYY7kz3l2FleytJYg/qWb8t2KZ/0PN+1hgI6uEUr2jpHKpGlquExVaS0VbjUZL7WxaqIXK6ADQ0n9GNfv9XCttWnD/O57t0TKFklnF3g5fJ/seoaa2D4O1x0F4PlgO9oIftbgFgYMfLgjACGqj0vlsddoUnj+Kt/mxunq72RP+UGqYjWMTA7R+b6dUCSEGEF5hoJQip6BaFs4HJtCyRrKs6wHCovDip/kys0WWpovMpOYBCtoT2N9B5uzWG0Zid8gnFrVFEQDtBaUrxEgXBimaEeER2/uIiK4roPOaMRYjBKsFly3fOO3G06dETGCWIsYjckprMphtEKMAQtgsMYi1mJMQHJ6xvkDKQoyphCzkl0AAAAASUVORK5CYII=) }
                #left-panel div.failure { background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAOCAYAAADwikbvAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sDEBUJOEC5CU8AAAAdaVRYdENvbW1lbnQAAAAAAENyZWF0ZWQgd2l0aCBHSU1QZC5lBwAAAeVJREFUKM+NkDtok2EUhp8vl9ZLo/EyKI6KFgqCKC4OClrBWUQEcRRx1cGpk3WyInWrgoMZKkW8thYaEYQ0i7WC2ngrNDTERHJvkv/L/3//dxwc7F8jeOAsh/c973OOEhG61aPnaen7maXYt4MLZ4+pbppQt+F06jNH3QWOb8pxUs+SmJzjv83hxY8SVy3wNdtVneiHqe54IhLoB4/TUkyMyOrKj5yXoVtPZK02kLyYK7OnlqFWzgcCGtUC/YUJ3n5a/jd28tU7ORTN0myUA6Jms8bpWIa798elqzn1fokjThrpVBC3ETzNbYAuca59j/Hp+b/N869Tsk8tgVMCXQk+RlfQuk1/tMLMwzsSMCcm5zjhvoR2AdpF0GuwO4aqttS05ZSbZHhsBoAIwI83Cdkd/460XDAOG02d24MxvlR8dsUUh3f2UHaEtgdbWCHz4oZwcVCp66PP5FLhKjEc8DXaCMsNy8DYn/SnZ+L0hhWOb/F8yLs9fDtwk8j+VpqwrlC34PrgGEu2bhlYhZ1b8dncq3AMeBaUr/k6NUyk4ChKzu+N2hc6Bqody+WDG8g2fLatD7F3axjPgmvAtYJvIbouhhIRrl0ZktnkBGIt1gqeMXQ8D2MMiCIUCqFEsFhEQMSykCuqX0MzLAUJTzRsAAAAAElFTkSuQmCC) }
                #left-panel div.detail { display: none }
                #right-panel { position: absolute; right: 0; top: 0; bottom: 0; left: 401px; overflow: auto; background: white }
                #right-panel .group { font-size: 12px; font-weight: bold; line-height: 16px; padding: 0 0 0 18px; counter-reset: assertion; background-repeat: repeat-x; background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAQCAYAAADXnxW3AAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sDEBUkDq8pxjkAAAAdaVRYdENvbW1lbnQAAAAAAENyZWF0ZWQgd2l0aCBHSU1QZC5lBwAAADdJREFUCNdVxrERwDAMAzGK0v47eS6Z927SpMFBAAbkvSvnRk5+7K5cVfLMyN39bWakJAjA5xw9R94jN3tVhVEAAAAASUVORK5CYII=) }
                #right-panel .zebra { background-repeat: repeat; padding: 0 0 0 18px; background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAAmCAYAAAAFvPEHAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sDEBYWFlNztEcAAAAdaVRYdENvbW1lbnQAAAAAAENyZWF0ZWQgd2l0aCBHSU1QZC5lBwAAABdJREFUCNdjYKAtePv5338mBgYGBpoQAGy1BAJlb/y6AAAAAElFTkSuQmCC) }
                #right-panel .data { line-height: 19px; white-space: nowrap }
                #right-panel pre.data { white-space: pre }
                #right-panel tbody.failure { color: red }
                #right-panel td.key { min-width: 108px }
                #right-panel td.delimiter { min-width: 18px }
                #right-panel td.assertion:before { counter-increment: assertion; content: counter(assertion) ". " }
                #right-panel td.assertion { color: black }
                #right-panel .trail { border-top: 1px solid #b4b4b4 }
				#left-panel  ul.myHide{ /* 隐藏子菜单 */
					display:none;
					}
				#left-panel  ul.myShow{ /* 显示子菜单 */
					display:block;
				}
				#left-panel  li.li_Hide{ /* 隐藏子菜单 */
					display:none;
					}
				#left-panel  li.li_Show{ /* 显示子菜单 */
					display:block;
				}
				
				#right-panel div.totalDetail{
					font:normal 68% verdana,arial,helvetica;
					color:#000000;
				    margin: 1px; padding: 0
				}
				
				
               
				table tr td, table tr th {
					font-size: 68%;	
					border: solid #fffeff;
					border-width: 2px 0px 0px 2px;					
				}
				table.details tr th{
				    color: #ffffff;
					font-weight: bold;
					text-align:center;
					background:#2674a6;
					white-space: pre-wrap;
				}
				table.details tr td{
					background:#eeeee0;
					white-space: pre-wrap;
					border: solid #fffeff;
					border-width: 2px 0px 0px 2px;
					
				}

				table.child_details tr th{
				    color: #ffffff;
					font-weight: bold;
					text-align:center;
					background:#7c886a;
					white-space: pre-wrap;
				}

				table.child_details tr td{
					background:#eeeee0;
					white-space: pre-wrap;
					border: solid #fffeff;
					border-width: 2px 0px 0px 2px;
                    padding: 5px 5px 5px 5px;
				}
                table.thread_details tr th{
				    color: #ffffff;
					font-weight: bold;
					text-align:center;
					background:#9da8b2;
					white-space: pre-wrap;
				}

				table.thread_details tr td{
					background:#eeeee0;
					white-space: pre-wrap;
					border: solid #fffeff;
					border-width: 2px 0px 0px 2px;

				}

				h1 {
					margin: 0px 0px 5px; font: 165% verdana,arial,helvetica
				}
				h2 {
					margin-top: 1em; margin-bottom: 0.5em; font: bold 125% verdana,arial,helvetica
				}
				h3 {
					margin-bottom: 0.5em; font: bold 115% verdana,arial,helvetica
				}
				.Failure {
					font-weight:bold; color:red;
				}
				
	
				img
				{
				  border-width: 0px;
				}
				
				.expand_link
				{
				   position=absolute;
				   right: 0px;
				   width: 27px;
				   top: 1px;
				   height: 27px;
				}
				
				.page_details
				{
				   display: none;
				}
                                
                .page_details_expanded
                {
                    display: block;
                    display/* hide this definition from  IE5/6 */: table-row;
                }

            ]]></style>
                <script type="text/javascript"><![CDATA[
				//全局变量，用于定义上一个选中的li
                window.last_li = null;
				
                var onclick_li = (function() {
                    var last_selected = null;
                    return function(li) {
					
                        if( last_selected == li )
						{
							li.className = "selected";
							document.getElementById("right-panel").innerHTML = li.firstChild.nextSibling.innerHTML;
							return ;
						}
                            
                        if( last_selected )
                            last_selected.className = "";
                        last_selected = li;
                        last_selected.className = "selected";
						window.last_li = last_selected;
                        document.getElementById("right-panel").innerHTML = last_selected.firstChild.nextSibling.innerHTML;
						//alert(li.text);
                        return false;
                    };
                })();
				
				//事物父节点点击操作
				var onclick_li_parent = (function() {
                    var last_selected = null;
					
                    return function(li) {
					console.log(window.last_li);
						if(window.last_li && window.last_li.className != "")
							window.last_li.className = "";					
					
                        if( last_selected == li )
						{
							document.getElementById("right-panel").innerHTML = last_selected.nextSibling.innerHTML;
							return;
						}
                            
                        					                            
                        last_selected = li;						
                        
						document.getElementById("right-panel").innerHTML = last_selected.nextSibling.innerHTML;
                        
                        return false;
                    };
                })();
                
                var patch_timestamp = function() {
                    var spans = document.getElementsByTagName("span");
                    var len = spans.length;
                    for( var i = 0; i < len; ++i ) {
                        var span = spans[i];
                        if( "patch_timestamp" == span.className )
						{
							var date = new Date(parseInt( span.innerHTML ));
								Y = date.getFullYear() + '-';
								M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
								D = date.getDate() + ' ';
								h = date.getHours() + ':';
								m = date.getMinutes() + ':';
								s = date.getSeconds(); 	
							span.innerHTML = Y+M+D+h+m+s;								
						}
                            //span.innerHTML = new Date(parseInt( span.innerHTML ) );
                    }
                };
				var patch_decode_url = function() {
                    var spans = document.getElementsByTagName("span");
                    var len = spans.length;
                    for( var i = 0; i < len; ++i ) {
                        var span = spans[i];
                        if( "patch_decode_url" == span.className )
                            span.innerHTML = decodeURIComponent(span.innerHTML);
                    }
                };
				
				var click_firstThread = function(li){
					//var totalDiv = document.getElementById("total-detail");
					document.getElementById("right-panel").innerHTML = totalDiv.innerHTML;
					if(li.style.backgroundImage == "url(\"collapse.png\")"){
						li.style.backgroundImage = "url(\"expand.png\")";
					}
					else{
						li.style.backgroundImage = "url(\"collapse.png\")";
					}
					console.log(li.style.backgroundImage);
				};
				
				var change = function(el){
					//点击线程级li元素，找到子元素ul
					
					var oSecondDiv = el.parentNode.getElementsByTagName("ul")[0];
					var parentCss = el.parentNode;
					
					
					//CSS交替更换来实现显、隐
					if(oSecondDiv.className == "myHide")
					{
						oSecondDiv.className = "myShow";
						//$(parentCss).css("background-image","url(collapse.png)");
						parentCss.style.backgroundImage="url(collapse.png)";
						//console.log(parentCss.style.backgroundImage);
					}
					else
					{
						oSecondDiv.className = "myHide";
						//$(parentCss).css("background-image","url(expand.png)");
						parentCss.style.backgroundImage="url(expand.png)";
						//console.log(parentCss.style.backgroundImage);
					}
					
				};
				
				
                
                //标记线程组里是否有执行失败的用例，如果有，线程组标记为红色
				var patch_navigation_class=(function() {

					var set_class = function(el, flag) {
						if(el) {
							el.className += flag ? " success" : " failure";
						}
					};

					var traverse = function (el) {
						//el线程组名称节点
						while (1) {
							if (el) {
								var u = el.firstChild;
								u = u ? el.children[1] : null;
								//console.log(u);
								if (u) {
									var o = u.children[0];  //用例节点
									//console.log(o);
									while (1) {
										if (o) {
											var v = o.firstChild; //第一个div节点，标记用例是否成功
											//v = v ? v.nextSibling :null;
										    //console.log( v);
											if (v && v.className == 'failure') {
												set_class(el, false);
												break;
											}
											else {
												o = o.nextSibling; //下一个用例
											}
										}
										else {
											set_class(el, true);
											break;
										}
									}
								}
								el = el.nextSibling; //下一个线程组
							}
							else {
								break;
							}
						}
					};
					return function () {
						var o = document.getElementById("result-list");
						//o.children[0] == o.childNodes[1]
						o = o ? o.children[0] : null;
						if (o)
							traverse(o);
					};
				})();
				
				//存放统计内容对象
				var totalDiv = null;
				
                window.onload = function() {
                    patch_timestamp();
					patch_decode_url();
                    patch_navigation_class();
                    totalDiv = document.getElementById("total-detail");
				
                };

               function expand(details_id)
			   {
			      
			      document.getElementById(details_id).className = "page_details_expanded";
			   }
			   
			   function collapse(details_id)
			   {
			      
			      document.getElementById(details_id).className = "page_details";
			   }
			   
			   function changeImage(details_id)
			   {
			      if(document.getElementById(details_id+"_image").src.match("expand"))
			      {
			         document.getElementById(details_id+"_image").src = "collapse.png";
			         expand(details_id);
			      }
			      else
			      {
			         document.getElementById(details_id+"_image").src = "expand.png";
			         collapse(details_id);
			      } 
                }
				
				function show_Li(flag)
				{
					var readli_ul=function(li)
					{
						if(li.getElementsByTagName("div")[0].className == "success" && flag == "failure")
                        {
                            li.style.display="none";
                            return;
                        }

                        if(li.getElementsByTagName("div")[0].className == "success" && (flag == "success" || flag=="ALL"))
                        {
                            li.style.display="block";
                            return;
                        }
                        var cDiv = li.getElementsByTagName("ul")[0];
						if(cDiv)
						{
							var list = cDiv.childNodes;
							for(var i = list.length; i--;)
							{
								readli_ul(list[i]);
							}

						}else
						{
                            var cln = li.firstChild.className;
							if (cln == flag || flag=="ALL")//子元素div的class
                                li.style.display="block";
							else
								li.style.display="none";
						}
					}

					var resultList = document.getElementById("result-list").childNodes;
                    for(var k =0; k < resultList.length; k++)
                    {
                        var rootNode = resultList[k];
                        var childList = rootNode.getElementsByTagName("ul")[0].childNodes; //获取li->ul下的li子元素数组
                        for(var i = childList.length; i--;)
                        {
                            readli_ul(childList[i]);
                        }
                    }
				}

                

            ]]></script>
            </head>
            <body>
                <div id="left-panel">
					<hr size="1" align="center" />
                    <div align="center">接口测试用例 </div>
                    <p> 
					<hr size="1" align="center" />
					<table align="center" class="details" border="0" cellpadding="5" cellspacing="2" width="100%">
                        <tr><td align="center" ><input type="radio" name="check_case" checked="checked" onclick="show_Li('ALL')">ALL</input></td>
                            <td align="center"><input type="radio" name="check_case" onclick="show_Li('success')">Success</input></td>
                            <td align="center"><input type="radio" name="check_case" onclick="show_Li('failure')">Fail</input></td>
                        </tr>
                    </table>
									
                    <hr size="1" align="center" />
					 </p>
                    <ul id="result-list">
                        <xsl:for-each select="*">
                            <!-- group with the previous sibling -->                  
                            <xsl:if test="position() = 1 or @tn != preceding-sibling::*[1]/@tn">								    
                                <xsl:call-template name="left"/>li class="navigation"  <xsl:call-template name="right"/><a onclick="change(this);click_firstThread(this);" style="padding: 0 0 0 15px; background-image: url(collapse.png);background-repeat:no-repeat;">Thread: <xsl:value-of select="@tn"/></a>
								<xsl:call-template name="left"/>ul class = "myShow" <xsl:call-template name="right"/> 
                            </xsl:if>
							<xsl:if test="self::sample| self::httpSample">								    
							<xsl:choose>
								<xsl:when test="descendant::httpSample">
									<!--如果该节点还是事物类型，且存在子节点 -->
									<xsl:call-template name="transaction-li-node1" />
								</xsl:when>
								<xsl:otherwise><xsl:call-template name="normal-li-node" /></xsl:otherwise>
								<!--
								 <xsl:when test="child::httpSample and number(substring(@lb, 1, 1)+1)">
									<xsl:call-template name="normal-li-node" />
								</xsl:when>
								 -->
							</xsl:choose>												
							</xsl:if>
                            <xsl:if test="@tn != following-sibling::*[1]/@tn">
							                                   
								<xsl:call-template name="left"/>/ul<xsl:call-template name="right"/>
								<xsl:call-template name="left"/>/li<xsl:call-template name="right"/>
                            </xsl:if>
							
                        </xsl:for-each>
                    </ul>
                </div>
                <div id="right-panel">	<div id="total-detail" class="totalDetail">
                    <xsl:call-template name="totalDetail" />
                </div></div>
            </body>
        </html>
    </xsl:template>
	
	   <!--普通li节点处理 -->
    <xsl:template name="normal-li-node">
		<xsl:if test="number(substring(@lb, 1, 1)+1) or (@lb='用户未登录调用接口')">	
        <li onclick="return onclick_li(this);">
		<xsl:attribute name="id"><xsl:text/>node_<xsl:value-of select="position()" /></xsl:attribute>
            <div>
                <xsl:attribute name="class">
                    <xsl:choose>
                        <xsl:when test="@s = 'true'">success</xsl:when>
                        <xsl:otherwise>failure</xsl:otherwise>
                    </xsl:choose>
                </xsl:attribute>
                <xsl:value-of select="@lb"/>
            </div>
            <xsl:call-template name="li-div-detail" />
        </li>
		</xsl:if>
    </xsl:template>
	<!--有子节点的事物节点 -->
    <xsl:template name="transaction-li-node1">
        <li class="navigation_parent" style="background-image: url(collapse.png)">
        <div onclick="change(this);onclick_li_parent(this);">
            <xsl:attribute name="class">
                <xsl:choose>
                    <xsl:when test="@s = 'true'">success</xsl:when>
                    <xsl:otherwise>failure</xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:value-of select="@lb"/>
        </div>
	
        <xsl:call-template name="li-div-detail" />
        <xsl:call-template name="transaction-li-child1" />
    	</li>

    </xsl:template>
    <!--事物节点的子节点处理 -->
    <xsl:template name="transaction-li-child1">
        <xsl:call-template name="left"/>ul class = "myShow" <xsl:call-template name="right"/>
        <xsl:for-each select="current()/httpSample|sample ">
            <!--<div>111lable=======<xsl:value-of select="@lb"/></div>-->
			<xsl:if test="self::sample| self::httpSample">
            <xsl:choose>
                <xsl:when test="descendant::httpSample">
                    <!--如果该节点还是事物类型，且存在子节点 -->
                    <xsl:call-template name="transaction-li-node1" />
                </xsl:when>
				<!--
				<xsl:when test="child::httpSample and number(substring(@lb, 1, 1)+1)">
					<xsl:call-template name="normal-li-node" />
				</xsl:when>				
                -->
				
				<xsl:otherwise>
                    <xsl:call-template name="normal-li-node" /> 
                </xsl:otherwise>
				
            </xsl:choose>
			</xsl:if>
        </xsl:for-each>
        <xsl:call-template name="left"/>/ul<xsl:call-template name="right"/>
    </xsl:template>
    <!--li节点展示的detail-div详情内容 -->
    <xsl:template name="li-div-detail">
        <div class="detail">

            <div class="group">Sampler</div>
            <div class="zebra">
                <table>
                    <tr><td class="data key">Thread Name</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@tn"/></td></tr>
                    <tr><td class="data key">TestCase Name</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@lb"/></td></tr>
                    <tr><td class="data key">Timestamp</td><td class="data delimiter">:</td><td class="data"><span class="patch_timestamp"><xsl:value-of select="@ts"/></span></td></tr>
                    <tr><td class="data key">Time</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@t"/> ms</td></tr>
                    <tr><td class="data key">Latency</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@lt"/> ms</td></tr>
                    <tr><td class="data key">Bytes</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@by"/></td></tr>
                    <tr><td class="data key">Sample Count</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@sc"/></td></tr>
                    <tr><td class="data key">Error Count</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@ec"/></td></tr>
                    <tr><td class="data key">Response Code</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@rc"/></td></tr>
                    <tr><td class="data key">Response Message</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="@rm"/></td></tr>
					<tr><td class="data key"><strong>TestResult</strong></td><td class="data delimiter">:</td><td class="data">
					
					<xsl:choose>
						<xsl:when test="@s = 'true'"><span style="color:green;font-weight: bold;">Pass</span></xsl:when>
						<xsl:otherwise><span style="color:red;font-weight: bold;">Failed</span></xsl:otherwise>
					</xsl:choose>
					</td></tr>
                </table>
            </div>
            <div class="trail"></div>
            <xsl:if test="count(assertionResult) &gt; 0">
                <div class="group">Assertion</div>
                <div class="zebra">
                    <table>
                        <xsl:for-each select="assertionResult">
                            <tbody>
                                <xsl:attribute name="class">
                                    <xsl:choose>
                                        <xsl:when test="failure = 'true'">failure</xsl:when>
                                        <xsl:when test="error = 'true'">failure</xsl:when>
                                    </xsl:choose>
                                </xsl:attribute>
                                <tr><td class="data assertion" colspan="3"><xsl:value-of select="name"/></td></tr>
                                <tr><td class="data key">Failure</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="failure"/></td></tr>
                                <tr><td class="data key">Error</td><td class="data delimiter">:</td><td class="data"><xsl:value-of select="error"/></td></tr>
                                <tr><td class="data key">Failure Message</td><td class="data delimiter">:</td><td class="data"><pre class="data"><xsl:value-of select="failureMessage"/></pre></td></tr>
                            </tbody>
                        </xsl:for-each>
                    </table>
                </div>
                <div class="trail"></div>
            </xsl:if>
			<xsl:if test="self::httpSample">
            <div class="group">Request</div>
            <div class="zebra">
                <table>
                    <tr><td class="data key">Method/Url</td><td class="data delimiter">:</td><td class="data"><pre class="data"><xsl:value-of select="method"/><xsl:text> </xsl:text><xsl:value-of select="java.net.URL"/></pre></td></tr>
                    <tr><td class="data key">Query String</td><td class="data delimiter">:</td><td class="data"><pre class="data"><span class="patch_decode_url"><xsl:value-of select="queryString"/></span></pre></td></tr>
                    <tr><td class="data key">Cookies</td><td class="data delimiter">:</td><td class="data"><pre class="data"><xsl:value-of select="cookies"/></pre></td></tr>
                    <tr><td class="data key">Request Headers</td><td class="data delimiter">:</td><td class="data"><pre class="data"><xsl:value-of select="requestHeader"/></pre></td></tr>
                </table>
            </div>
            <div class="trail"></div>
            <div class="group">Response</div>
            <div class="zebra">
                <table>
                    <tr><td class="data key">Response Headers</td><td class="data delimiter">:</td><td class="data"><pre class="data"><xsl:value-of select="responseHeader"/></pre></td></tr>
                    <tr><td class="data key">Response Data</td><td class="data delimiter">:</td><td class="data"><pre class="data"><xsl:value-of select="responseData"/></pre></td></tr>
                    <tr><td class="data key">Response File</td><td class="data delimiter">:</td><td class="data"><pre class="data"><xsl:value-of select="responseFile"/></pre></td></tr>
                </table>
            </div>
			</xsl:if>
            <div class="trail"></div>

        </div>
    </xsl:template>

	<xsl:template name="left">

	<xsl:text disable-output-escaping="yes">&lt;</xsl:text>

	</xsl:template>

	<xsl:template name="right">

	<xsl:text disable-output-escaping="yes">&gt;</xsl:text>

	</xsl:template>
	
	<xsl:template name="totalDetail">
	
		<xsl:call-template name="pageHeader" />
			
		<xsl:call-template name="summary" />
		<hr size="1" align="center" />
			
		<xsl:call-template name="pagelist" />
		<hr size="1" align="center" />
	</xsl:template>

    <xsl:template name="pageHeader">
        <h1><xsl:value-of select="$titleReport" /></h1>
		<table width="100%" align="center">
			<tr>
				<td align="left">Date report: <xsl:value-of select="$dateReport" /></td>
				<td align="right">Designed for use with <a href="http://jmeter.apache.org/">JMeter</a> and <a href="http://ant.apache.org">Ant</a>.</td>
			</tr>
		</table>
		<hr size="1" />
    </xsl:template>

    <xsl:template name="summary">
        <h2>Summary</h2>
        <table align="center" class="details" border="0" cellpadding="5" cellspacing="2" width="100%">
            <tr valign="top">
                <th>实例</th>
                <th>失败</th>
                <th>成功率</th>
                <th>平均时间</th>
                <th>最短时间</th>
                <th>最长时间</th>
            </tr>
            <tr valign="top">
				<xsl:variable name="allCount" select="count(//sample) + count(//httpSample) - count(/testResults/sample[(count(child::httpSample) + count(child::sample))> 0])"/>
                <xsl:variable name="allFailureCount" select="count(//sample[attribute::s='false']) + count(//httpSample[attribute::s='false'])- count(/testResults/sample[count(child::httpSample) > 0][attribute::s='false'])"/>
                <!--<xsl:variable name="allCount" select="count(/testResults/*)" />
                <xsl:variable name="allFailureCount" select="count(/testResults/*[attribute::s='false'])" />
                <xsl:variable name="allSuccessCount" select="count(/testResults/*[attribute::s='true'])" />-->
                <xsl:variable name="allSuccessPercent" select="($allCount - $allFailureCount) div $allCount" />
                <xsl:variable name="allTotalTime" select="sum(//sample/@t) + sum(//httpSample/@t) - sum(//sample[(count(child::httpSample) + count(child::sample)) > 0]/@t)" />
                <xsl:variable name="allAverageTime" select="$allTotalTime div $allCount" />
                <xsl:variable name="allMinTime">
                    <xsl:call-template name="min">
                        <xsl:with-param name="nodes" select="(//sample|//httpSample)/@t" />
                    </xsl:call-template>
                </xsl:variable>
                <xsl:variable name="allMaxTime">
                    <xsl:call-template name="max">
                        <xsl:with-param name="nodes" select="(//sample|//httpSample)/@t" />
                    </xsl:call-template>
                </xsl:variable>
                <xsl:attribute name="class">
                    <xsl:choose>
                        <xsl:when test="$allFailureCount &gt; 0">Failure</xsl:when>
                    </xsl:choose>
                </xsl:attribute>
				
                <td align="center">
                    <xsl:value-of select="$allCount" />
                </td>
                <td align="center">
                    <xsl:value-of select="$allFailureCount" />
                </td>
                <td align="center">
                    <xsl:call-template name="display-percent">
                        <xsl:with-param name="value" select="$allSuccessPercent" />
                    </xsl:call-template>
                </td>
                <td align="center">
                    <xsl:call-template name="display-time">
                        <xsl:with-param name="value" select="$allAverageTime" />
                    </xsl:call-template>
                </td>
                <td align="center">
                    <xsl:call-template name="display-time">
                        <xsl:with-param name="value" select="$allMinTime" />
                    </xsl:call-template>
                </td>
                <td align="center">
                    <xsl:call-template name="display-time">
                        <xsl:with-param name="value" select="$allMaxTime" />
                    </xsl:call-template>
                </td>
            </tr>
        </table>
    </xsl:template>


<xsl:template name="pagelist">
	<h2>Pages</h2>
	<table align="center" class="details" border="0" cellpadding="5" cellspacing="2" width="100%" style="table-layout：fixed;">
		<tr valign="top">
			<th>TestcaseName</th>
			<th># Samples</th>
			<th>Failures</th>
			<th>Success Rate</th>
			<th>Average Time</th>
			<th>Min Time</th>
			<th>Max Time</th>
			<th></th>
		</tr>

		<xsl:for-each select="/testResults/*[not(@lb = preceding::*/@lb)]">
            <xsl:variable name="label" select="@lb" />
            <xsl:variable name="count">
                <xsl:call-template name="getCount">
                    <xsl:with-param name="count_" select="current()"/>
                </xsl:call-template>
            </xsl:variable>
            <xsl:variable name="failureCount">
                <xsl:call-template name="getFailureCount">
                    <xsl:with-param name="getFailureCountt_" select="current()"/>
                </xsl:call-template>
            </xsl:variable>
            <xsl:variable name="successCount" select="count(../*[@lb = current()/@lb][attribute::s='true'])" />
            <xsl:variable name="successPercent" select="($count - $failureCount) div $count" />
            <xsl:variable name="totalTime" select="sum(../*[@lb = current()/@lb]/@t)" />
            <xsl:variable name="averageTime" select="$totalTime div $count" />

 			<xsl:variable name="minTime">
				<xsl:call-template name="min">

					<xsl:with-param name="nodes" select="../*[@lb = current()/@lb]/@t" />
				</xsl:call-template>
			</xsl:variable>
			<xsl:variable name="maxTime">
				<xsl:call-template name="max">
					<xsl:with-param name="nodes" select="../*[@lb = current()/@lb]/@t" />
				</xsl:call-template>
			</xsl:variable>
			<tr valign="top">
				<xsl:attribute name="class">
					<xsl:choose>
						<xsl:when test="$failureCount &gt; 0">Failure</xsl:when>
					</xsl:choose>
				</xsl:attribute>
				<td style="word-wrap：break-word">
				<xsl:if test="$failureCount > 0">
				  <a><xsl:attribute name="href">#<xsl:value-of select="$label" /></xsl:attribute>
				  <xsl:value-of select="$label" />
				  </a>
				</xsl:if>
				<xsl:if test="0 >= $failureCount">
				  <xsl:value-of select="$label" />
				</xsl:if>
				</td>
				<td align="center">
					<xsl:value-of select="$count" />
				</td>
				<td align="center">
					<xsl:value-of select="$failureCount" />
				</td>
				<td align="right">
					<xsl:call-template name="display-percent">
						<xsl:with-param name="value" select="$successPercent" />
					</xsl:call-template>
				</td>
				<td align="right">
					<xsl:call-template name="display-time">
						<xsl:with-param name="value" select="$averageTime" />
					</xsl:call-template>
				</td>
				<td align="right">
					<xsl:call-template name="display-time">
						<xsl:with-param name="value" select="$minTime" />
					</xsl:call-template>
				</td>
				<td align="right">
					<xsl:call-template name="display-time">
						<xsl:with-param name="value" select="$maxTime" />
					</xsl:call-template>
				</td>
                <xsl:choose>
                    <xsl:when test="descendant::httpSample|sample ">
                        <td align="center">
                            <a href="">
                                <xsl:attribute name="href"><xsl:text/>javascript:changeImage('page_details_<xsl:value-of select="position()" />_<xsl:value-of
                                        select="substring(@tn, string-length(@tn)- 2, string-length(@tn))" />')</xsl:attribute>
                                <img src="expand.png" alt="expand/collapse"><xsl:attribute name="id"><xsl:text/>page_details_<xsl:value-of select="position()" />_<xsl:value-of
                                        select="substring(@tn, string-length(@tn)- 2, string-length(@tn))" />_image</xsl:attribute></img>
                            </a>
                        </td>
                    </xsl:when>
                    <xsl:otherwise>
                        <td align="center">
                            <a href="">
                                <xsl:attribute name="href"><xsl:text/>javascript:changeImage('page_details_<xsl:value-of select="position()" />')</xsl:attribute>
                                <img src="expand.png" alt="expand/collapse"><xsl:attribute name="id"><xsl:text/>page_details_<xsl:value-of select="position()" />_image</xsl:attribute></img>
                            </a>
                        </td>
                    </xsl:otherwise>
                </xsl:choose>

			</tr>
			

                         <!-- 包含子节点用例-->
                         <xsl:choose>
                             <xsl:when test="descendant::httpSample|sample ">
                                 <tr class="page_details">
                                 <xsl:variable name="parent_position" select="position()"/>
                                 <xsl:attribute name="id"><xsl:text/>page_details_<xsl:value-of select="$parent_position" />_<xsl:value-of
                                         select="substring(@tn, string-length(@tn)- 2, string-length(@tn))" /></xsl:attribute>
                                 <td colspan="8" bgcolor="#FF0000">
                                 <div align="center">
                                 <b>Details for Page "<xsl:value-of select="$label" />"</b>
                                 <table bordercolor="#000000" bgcolor="#2674A6" border="0"  cellpadding="1" cellspacing="1" width="95%" class="child_details">
                                 <tr valign="top">
                                     <th id="th_style">TestcaseName</th>
                                     <th id="th_style"># Samples</th>
                                     <th id="th_style">Failures</th>
                                     <th id="th_style">Success Rate</th>
                                     <th id="th_style">Average Time</th>
                                     <th id="th_style">Min Time</th>
                                     <th id="th_style">Max Time</th>
                                     <!--<th id="th_style"></th>-->
                                 </tr>
                                 <xsl:for-each select="descendant::httpSample|sample">
                                     <xsl:variable name="label_c" select="@lb" />
                                     <xsl:variable name="count_c">
                                         <xsl:call-template name="getCount">
                                             <xsl:with-param name="count_" select="current()"/>
                                         </xsl:call-template>
                                     </xsl:variable>
                                     <xsl:variable name="failureCount_c">
                                         <xsl:call-template name="getFailureCount">
                                             <xsl:with-param name="getFailureCountt_" select="current()"/>
                                         </xsl:call-template>
                                     </xsl:variable>
                                     <xsl:variable name="successCoun_c" select="count(../*[@lb = current()/@lb][attribute::s='true'])" />
                                     <xsl:variable name="successPercent_c" select="($count_c - $failureCount_c) div $count_c" />
                                     <xsl:variable name="totalTime_c" select="sum(../*[@lb = current()/@lb]/@t)" />
                                     <xsl:variable name="averageTime_c" select="$totalTime_c div $count_c" />

                                     <xsl:variable name="minTime_c">
                                         <xsl:call-template name="min">

                                             <xsl:with-param name="nodes" select="../*[@lb = current()/@lb]/@t" />
                                         </xsl:call-template>
                                     </xsl:variable>
                                     <xsl:variable name="maxTime_c">
                                         <xsl:call-template name="max">
                                             <xsl:with-param name="nodes" select="../*[@lb = current()/@lb]/@t" />
                                         </xsl:call-template>
                                     </xsl:variable>
                                     <tr valign="top">
                                         <xsl:attribute name="class">
                                             <xsl:choose>
                                                 <xsl:when test="$failureCount_c &gt; 0">Failure</xsl:when>
                                             </xsl:choose>
                                         </xsl:attribute>
                                         <td style="word-wrap：break-word">
                                             <xsl:if test="$failureCount_c > 0">
                                                 <a>
                                                     <!--<xsl:attribute name="href">#<xsl:value-of select="$label" /></xsl:attribute>-->
                                                     <xsl:value-of select="$label_c" />
                                                 </a>
                                             </xsl:if>
                                             <xsl:if test="0 >= $failureCount_c">
                                                 <xsl:value-of select="$label_c" />
                                             </xsl:if>
                                         </td>
                                         <td align="center">
                                             <xsl:value-of select="$count_c" />
                                         </td>
                                         <td align="center">
                                             <xsl:value-of select="$failureCount_c" />
                                         </td>
                                         <td align="right">
                                             <xsl:call-template name="display-percent">
                                                 <xsl:with-param name="value" select="$successPercent_c" />
                                             </xsl:call-template>
                                         </td>
                                         <td align="right">
                                             <xsl:call-template name="display-time">
                                                 <xsl:with-param name="value" select="$averageTime_c" />
                                             </xsl:call-template>
                                         </td>
                                         <td align="right">
                                             <xsl:call-template name="display-time">
                                                 <xsl:with-param name="value" select="$minTime_c" />
                                             </xsl:call-template>
                                         </td>
                                         <td align="right">
                                             <xsl:call-template name="display-time">
                                                 <xsl:with-param name="value" select="$maxTime_c" />
                                             </xsl:call-template>
                                         </td>
                                         <!--<td align="center">-->
                                             <!--<a href="">-->
                                                 <!--<xsl:attribute name="href"><xsl:text/>javascript:changeImage('page_details_<xsl:value-of select="$parent_position"/>_<xsl:value-of select="position()" />')</xsl:attribute>-->
                                                 <!--<img src="expand.png" alt="expand/collapse"><xsl:attribute name="id"><xsl:text/>page_details_<xsl:value-of select="$parent_position"/>_<xsl:value-of select="position()" />_image</xsl:attribute></img>-->
                                                 <!--<xsl:value-of  select="substring(@tn, string-length(@tn)- 2, string-length(@tn))" />-->
                                             <!--</a>-->
                                         <!--</td>-->
                                     </tr>

                                 </xsl:for-each>
                                 </table>
                                 </div>
                                 </td>
                                 </tr>
                             </xsl:when>
                             <xsl:otherwise>
                                 <tr class="page_details">
                                 <xsl:attribute name="id"><xsl:text/>page_details_<xsl:value-of select="position()" /></xsl:attribute>
                                 <td colspan="8" bgcolor="#FF0000">
                                 <div align="center">
                                 <b>Details for Page "<xsl:value-of select="$label" />"</b>
                                 <table bordercolor="#000000" bgcolor="#2674A6" border="0"  cellpadding="1" cellspacing="1" width="95%" class="thread_details">
                                 <tr>
                                     <th>Thread</th>
                                     <th>Iteration</th>
                                     <th>Time (milliseconds)</th>
                                     <th>Bytes</th>
                                     <th>Success</th>
                                 </tr>

                                 <xsl:for-each select="../*[@lb = $label and @tn != $label]">
                                     <tr>
                                         <td><xsl:value-of select="@tn" /></td>
                                         <td align="center"><xsl:value-of select="position()" /></td>
                                         <td align="right"><xsl:value-of select="@t" /></td>
                                         <!--  TODO allow for missing bytes field -->
                                         <td align="right"><xsl:value-of select="@by" /></td>
                                         <td align="center"><xsl:value-of select="@s" /></td>
                                     </tr>
                                 </xsl:for-each>
                                 </table>
                                 </div>
                                 </td>
                                 </tr>
                             </xsl:otherwise>
                         </xsl:choose>
                         <!-- TODO-->
		</xsl:for-each>
	</table>
</xsl:template>
<!-- 线程数据统计-->
    <xsl:template name="show_thread">
        <xsl:param name="nodes" select="current()"/>
        <xsl:variable name="label" select="@lb" />
        <tr class="page_details">
            <xsl:attribute name="id"><xsl:text/>page_details_<xsl:value-of select="position()" /></xsl:attribute>
            <td colspan="8" bgcolor="#FF0000">
                <div align="center">
                    <b>Details for Page "<xsl:value-of select="$label" />"</b>
                    <table bordercolor="#000000" bgcolor="#2674A6" border="0"  cellpadding="1" cellspacing="1" width="95%" class="thread_details">
                        <tr>
                            <th>Thread</th>
                            <th>Iteration</th>
                            <th>Time (milliseconds)</th>
                            <th>Bytes</th>
                            <th>Success</th>
                        </tr>

                        <xsl:for-each select="../*[@lb = $label and @tn != $label]">
                            <tr>
                                <td><xsl:value-of select="@tn" /></td>
                                <td align="center"><xsl:value-of select="position()" /></td>
                                <td align="right"><xsl:value-of select="@t" /></td>
                                <!--  TODO allow for missing bytes field -->
                                <td align="right"><xsl:value-of select="@by" /></td>
                                <td align="center"><xsl:value-of select="@s" /></td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </div>
            </td>
        </tr>

    </xsl:template>


    <xsl:template name="min">
        <xsl:param name="nodes" select="/.." />
        <xsl:choose>
            <xsl:when test="not($nodes)">NaN</xsl:when>
            <xsl:otherwise>
                <xsl:for-each select="$nodes">
                    <xsl:sort data-type="number" />
                    <xsl:if test="position() = 1">
                        <xsl:value-of select="number(.)" />
                    </xsl:if>
                </xsl:for-each>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="max">
        <xsl:param name="nodes" select="/.." />
        <xsl:choose>
            <xsl:when test="not($nodes)">NaN</xsl:when>
            <xsl:otherwise>
                <xsl:for-each select="$nodes">
                    <xsl:sort data-type="number" order="descending" />
                    <xsl:if test="position() = 1">
                        <xsl:value-of select="number(.)" />
                    </xsl:if>
                </xsl:for-each>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="display-percent">
        <xsl:param name="value" />
        <xsl:value-of select="format-number($value,'0.00%')" />
    </xsl:template>

    <xsl:template name="display-time">
        <xsl:param name="value" />
        <xsl:value-of select="format-number($value,'0 ms')" />
    </xsl:template>

    <!--统计事务节点下用例总数 -->
	<xsl:template name="getCount">
        <xsl:param name="count_" select="/testResults/*[0]"/>
        <xsl:choose>
            <xsl:when test="(count(current()/child::sample) + count(current()/child::httpSample)) > 0">
                <xsl:value-of select="count(../*[@lb = current()/@lb ]) * (count(current()//child::sample) + count(current()//child::httpSample))"/>

        </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="count(../*[@lb = current()/@lb and @ts =  current()/@ts])"/>
            </xsl:otherwise>
        </xsl:choose>
	</xsl:template>

    <xsl:template name="getFailureCount">
        <xsl:param name="getFailureCountt_" select="/testResults/*[0]"/>
        <xsl:choose>
            <xsl:when test="(count(current()/child::sample) + count(current()/child::httpSample)) > 0">
                <xsl:value-of select="count(../*[@lb = current()/@lb]//httpSample[attribute::s='false']) + count(../*[@lb = current()/@lb]//sample[attribute::s='false'])"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="count(../*[@lb = current()/@lb and @ts =  current()/@ts][attribute::s='false'])"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="getFatherPosition">
        <xsl:variable name="Name" select="name(..)"/>
        <xsl:variable name="Position" select="count(../preceding-sibling::*[name()=$Name])+1"/>
        <xsl:value-of select="$Position"/>
    </xsl:template>

</xsl:stylesheet>


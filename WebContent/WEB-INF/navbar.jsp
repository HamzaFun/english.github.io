<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<li><a href="<c:url value="/pub/page_principale.jsp" />" class="active"><i class="fa fa-home" style="margin-right: 6px;"></i>Home</a></li>
<li class="type-menu"><a><i class="fa fa-plane" style="margin-right: 8px;"></i>Destination</a>
	<ul class="list-style">
		<c:forEach items="${ lvg }" var="vg">
		<li><a><c:out value="${ vg.continent }" /></a>
			<ul class="list-style">
				<c:forEach items="${ lvgp }" var="vgp" >
				<c:if test = "${ vg.continent == vgp.continent }" >
					<li>
						<a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="${ vgp.pays }" /> </c:url> "> ${ vgp.pays } </a>
					</li>
				</c:if>
				</c:forEach>                                
			</ul>
		</li>
		</c:forEach>                        
	</ul>
</li>
<li class="type-menu"><a>Types de voyages</a>
	<ul class="list-style">
		<li><a href="<c:url value="/pub/type_f.jsp" > <c:param name="type" value="circuit accompagne"  /> </c:url>" >Circuit accompagné</a></li>
		<li><a href="<c:url value="/pub/type_f.jsp" > <c:param name="type" value="touriste"  /> </c:url>">Touriste</a></li>
		<li><a href="<c:url value="/pub/type_f.jsp" > <c:param name="type" value="individuel"  /> </c:url>">Individuel</a></li>
		<li><a href="<c:url value="/pub/type_f.jsp" > <c:param name="type" value="famille"  /> </c:url>">Famille</a></li>
	</ul>
</li>
<li class="log">
	<a href="<c:choose>
					<c:when test="${ empty sessionScope.sesuser }"> <c:url value="/pub/login.jsp"/> </c:when>
					<c:otherwise> <c:url value="/List_demande#4"/> </c:otherwise>
			 </c:choose>"> Contact
	</a>
</li>
                
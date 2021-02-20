<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <c:if test="${ ! empty sesmapdml.size() && sesmapdml.size() != 0  }">
		<div class="bot">
			<i class="fa fa-plus"></i>
			<!-- compte le nombre des demandes  -->			
				<small><sub>${ sesmapdml.size() }</sub></small>
			<i class="fa fa-shopping-basket"></i>
        </div>
        </c:if>
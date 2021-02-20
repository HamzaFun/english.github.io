<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="recherche">
	<h3 class="title-des text-center"> rechercher à une voyage : </h3>
	<h5 class="text-center">( Il n'est pas nécessaire de remplir tous les champs )</h5>                
	<div class="form">
		<form action="<c:url value="/pub/rech_f.jsp"/>" method="POST">
			<select class="selinp" name="continent">
				<option value="" class="no">Continent</option>
				<c:forEach items="${ lvg }" var="vg">
				<option value="<c:out value="${ vg.continent }"/>"><c:out value="${ vg.continent }" /></option>
				</c:forEach>
			</select>
			<select class="selinp" name="pays">
				<option value="" class="no">Pays</option>
				<c:forEach items="${ lvgp }" var="vg">
				<option value="<c:out value="${ vg.pays }"/>"><c:out value="${ vg.pays }" /></option>
				</c:forEach>
			</select>
			<select class="selinp" name="ville">
				<option value="" class="no">Villes</option>
				<c:forEach items="${ all_v }" var="vg">
	            <option value="<c:out value="${ vg.ville }"/>"><c:out value="${ vg.ville }" /></option>
	            </c:forEach>
			</select>
			<select class="selinp" name="type_v">
				<option value="" class="no">Types de voyages</option>
				<option value="Circuit accompagné">Circuit accompagné</option>
				<option value="Touriste">Touriste</option>
				<option value="Individuel">Individuel</option>
				<option value="Famille">Famille</option>
			</select>
			<select class="selinp dure" name="dure_v">
				<option value="" class="no">Durée</option>
				<option value="Week-end/court séjour">Week-end/court séjour</option>
				<option value="1 semaine">1 semaine</option>
				<option value="2 semaine">2 semaine</option>
				<option value="3 semaine">3 semaine</option>
				<option value="Long séjour">Long séjour</option>
			</select>
			<input class="selinp date" type="text" name="date_v" placeholder="02/07/2020">
<!-- 			<input class="selinp temp" type="text" name="temp_v" placeholder="12:07"> -->
			<input type="submit" value="Recherche">                    
		</form>
	</div>
	<div class="ic">
		<i class="fa fa-star"></i> <i class="fa fa-star"></i>
		<i class="fa fa-star"></i> <i class="fa fa-star"></i>
		<i class="fa fa-star f"></i>
	</div>
</div>
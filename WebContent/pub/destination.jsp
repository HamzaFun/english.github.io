<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" >
    <link rel="stylesheet" href="<c:url value="/Css/normalize.css"/>" />
    <link rel="stylesheet" href="<c:url value="/Css/bootstrap.css"/>" />
    <link rel="stylesheet" href="<c:url value="/Css/insc.css"/>" />
    <link rel="icon" href="<c:url value="/images/logo-2.png"/>">
    <title>Agence-destinations</title>
</head>
<body id="sign-body">

    <!-- Démarrer la barre de navigation  -->
    <div class="responsive-bar">
        <div class="logo">
            <img src=" <c:url value="/images/logo-2.png" /> " />
        </div>
        <div class="menu float-left">
            <h4>Menu</h4>
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- barre de navigation -->
    <div class="nav-menu">
        <nav class="navbar-insc">
            <!-- logo de barre de navigation -->
            <div class="logo float-left">
                <img src=" <c:url value="/images/logo-2.png" /> " />
            </div>
            <!-- les listes de barre navigation -->
            <ul class="ul list-style">
                <!-- l'importation du fichier navbar -->
                <c:import url="/WEB-INF/navbar.jsp" />
                <c:choose>
                <c:when test="${ empty sessionScope.sesuser }">                
                <li><a href="<c:url value="/pub/login.jsp"/>">Login</a></li>
                </c:when>
                <c:otherwise><li><a href="<c:url value="/List_demande"/>"><c:out value="${ sesuser.nom }" /></a></li></c:otherwise>
                </c:choose>
                <c:choose>
                <c:when test="${ empty sessionScope.sesuser }">
                <li><a href="<c:url value="/pub/inscription.jsp"/>">Inscription</a></li>
                </c:when>
				<c:otherwise>
				<li><a href="<c:url value="/deconnexion"/>">deconnexion</a></li>
				</c:otherwise>
				</c:choose>
                <li>
                    <a href="<c:url value="/pub/rech_f.jsp"/>">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
                            <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                        </svg>
                    </a>
                </li>
            </ul>
        </nav>
        <div class="clearfix"></div>
    </div>
    <!-- Fin de la barre de navigation  -->
    <!-- l'importation du fichier Slide -->
      <c:import url="/WEB-INF/Slide.jsp" />
    
    <!-- le milieu de la page -->
    <section class="main-des">
        <!-- la partie en gauche contient tout les destinations d'un pays -->
        <div class="main">
            <c:import url="/WEB-INF/rech.jsp" />
            <c:choose>
            <c:when test="${ ! empty plvg }">
            <c:forEach items="${ plvg }" var="vg">
            <div class="des-ville">
                <!-- une description sur cette ville -->
                <div class="description">
                    <div class="img-des">
                        <img src="<c:url value="${ vg.lien }"/>" alt="Image de ${ vg.ville }" />
                        <div class="desc">
                        	<c:choose>
                         	<c:when test="${ ! empty vg && sessionScope.sesuser != null }" >
                            <div class="h4">
                                <div><h2> ${ vg.ville } </h2></div>
                                <div><h3> <span>Date  :</span>  ${ vg.date_v } </h3></div>
                                <div><h4> <span>Durée :</span>  ${ vg.dure_v } </h4></div>
                                <div><h5> <span>Prix  :</span>  ${ vg.prix }$ </h5></div>
                            </div>
                            <form action="<c:url value="/Add_demande_ses"/>" method="POST">
                                <input type="hidden" value="${ vg.continent }" name="continent"  >
                        		<input type="hidden" value="${ vg.pays }" name="v_pays"  >
                        		<input type="hidden" value="${ vg.ville }" name="ville"  >
                        		<input type="hidden" value="${ sesuser.email }" name="email"  >
                                <input type="hidden" value="${ vg.prix }" name="prix"  >
                                <input type="hidden" value="${ vg.dure_v }" name="dure_v"  >
								<input type="hidden" value="${ vg.date_v }" name="date_v"  >
								<input type="hidden" value="${ vg.type_v }" name="type_v" >                                
                        		<input type="hidden" value="${ vg.id_v }" name="id_v"  >
                        		<c:out value="${ form_dm.resultat }" />
                        		<c:if  test="${ vg.id_v != sessionScope.sesmapdml[vg.id_v].id_v && vg.id_v != sessionScope.sesmapdml_db[vg.id_v].id_v }">
                                	<input class="ajt" type="submit" name="rq_dm" value="Ajouter" />
                                </c:if>
                        		<c:if  test="${ vg.id_v == sessionScope.sesmapdml[vg.id_v].id_v }">                                    
                                	<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    	<path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                	</svg>
                                </c:if>
                            </form>
                            </c:when>
                            <c:otherwise>
                            <div class="h4">
                                <div><h2> ${ vg.ville } </h2></div>
                                <div><h3> <span>Date  :</span>  ${ vg.date_v } </h3></div>
                                <div><h4> <span>Durée :</span>  ${ vg.dure_v } </h4></div>
                                <div><h5> <span>Prix  :</span>  ${ vg.prix }$ </h5></div>
                            </div>
                            <form action="" method="POST">
                            	<a href="<c:url value="/pub/login.jsp"/>" >
                            		<input class="ajt" type="button"  name="" value="connecter svp">
                            	</a>
                            </form>
                            </c:otherwise>
							</c:choose>
                        </div>
                    </div>
                    
                </div>
                <div class="detprix">
                    <p class="lead"> 
                    	${ vg.description }
                    </p>
                </div>
            </div>
            </c:forEach>
            </c:when>
            <c:otherwise>
            <h2 class="vide text-center">Aucune voyage dans ce pays... </h2>
            </c:otherwise>
            </c:choose>
        </div>
        <!-- l'icon de Panier -->
        <c:import url="/WEB-INF/bot.jsp" />
    </section>
    <!-- la partie en bas  -->
    <!-- l'importation du fichier footer -->
       <c:import url="/WEB-INF/footer.jsp" />
    
    <!-- l'appelle de javascript -->
    <c:import url="/js/myScript.jsp" />

</body>
</html>
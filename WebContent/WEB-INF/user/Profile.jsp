<%@ page pageEncoding="UTF-8" %>
   <%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

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
    <title>Profile</title>
</head>
<body id="sign-body" class="height-bod">
	<!-- Démarrer la barre de navigation -->
    <div class="responsive-bar">
        <div class="logo">
            <img src=" <c:url value="/images/logo-2.png" /> " />
        </div>
        <div class="menu float-left">
            <h4>Menu</h4>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="nav-menu">
        <nav class="navbar-insc">
            <!-- logo de barre de navigation -->
            <div class="logo float-left">
                <img src=" <c:url value="/images/logo-2.png" /> " />
            </div>
            <!-- les listes de barre navigation -->
            <ul class="ul list-style nav-prof">
                <!-- l'importation du fichier navbar -->
                <c:import url="/WEB-INF/navbar.jsp" />
                <li class="dec"><a href="<c:url value="/deconnexion"/>">Deconnexion</a></li>
            </ul>
        </nav>
        <div class="clearfix"></div>
    </div>
    <!-- Fin de la barre de navigation -->
	<section class="sec-pro">
		<div class="prof">  
			<ul class="list-style">
				<li class="li_user_nom">
                    <div class="user">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle user-bi" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
                            <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                            <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
                        </svg>
                    </div>
                </li>
				<li class="li_user_nom li_nom"><a class="nam"> <c:out value="${ sesuser.nom }" /> <c:out value="${ sesuser.prenom }" /> <i class="fa fa-check-circle"></i></a></li>
				<li class="line"><a href="#1" class="a"><i class="fa fa-arrow-right"></i> Demande</a></li>
				<li><a href="#2" class="a"><i class="fa fa-check"></i> Demande accepté</a></li>
				<c:if test="${ sessionScope.sesuser.admin == true }" >
				<li class="msg"><a class="a"><i class="fa fa-comment"></i> Messages</a></li>
				<li><a href="#3" class="a"><i class="fa fa-plus-circle"></i> Ajouter voyage</a></li>				
                <li class="type-vg"><a class="a"><i class="fa fa-suitcase"></i> Tout les voyages</a></li>
                </c:if>
                <li><a href="#4" class="a"><i class="fa fa-envelope-square"></i> Contact</a></li>
                <li>
                    <a href="<c:url value="/pub/rech_f.jsp"/>" class="a">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
                            <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                        </svg>
                        Recherche
                    </a>
                </li>
				<li><a href="<c:url value="/deconnexion"/>" class="a"><i class="fa fa-sign-out"></i> Deconnexion</a>
			</ul>
		</div>
	</section>
	<section class="sec-tab">
        <h3 class="text-center msg">message</h3>
        <h3 class="text-center l-vg">Tout les voyages</h3>
        <!-- Les Demandes de client  -->
        
        <div class="vg-demande">
        	<c:if test="${ sessionScope.sesuser.admin == false }" >
            <h2 id="1" class="text-center">Tout les choix des voyages de votre demande :</h2>
            <c:forEach items="${ sessionScope.sesmapdml }" var="mapDM">
            <div class="container">
                <div class="detail-vg">
                    <ul class="list-style info-list">
                        <li class="supp"><c:out value="${ mapDM.value.continent }" /></li>
                        <li><c:out value="${ mapDM.value.ville }" /></li>
                        <li title="details" class="icon">
                            <svg width="1em" data-class="<c:out value="${ mapDM.value.ville }" />" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-compact-down selected" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M1.553 6.776a.5.5 0 0 1 .67-.223L8 9.44l5.776-2.888a.5.5 0 1 1 .448.894l-6 3a.5.5 0 0 1-.448 0l-6-3a.5.5 0 0 1-.223-.67z"/>
                            </svg>
                        </li>
                        <li title="Supprimer" class="del">
                        	<a href="<c:url value="/Supprimerses"><c:param name="id_v" value="${ mapDM.value.id_v }"/></c:url>">
                        		<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-dash-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path fill-rule="evenodd" d="M3.5 8a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.5-.5z"/>
                                </svg>
                        	</a>
                        </li>
                    </ul>
                </div>
                <div class="info-content">
                    <div class="<c:out value="${ mapDM.value.ville }" />">
                        <ul class="list-style">
                            <li><span>Dtae :</span> <c:out value="${ mapDM.value.date_v }" /> </li>
                            <li><span>Type de voyage :</span> <c:out value="${ mapDM.value.type_v }" /> </li>
                            <li><span>Durée :</span> <c:out value="${ mapDM.value.dure_v }" /> </li>
                            <li><span>Prix :</span> <c:out value="${ mapDM.value.prix }" />$ </li>
                        </ul>
                    </div>
                </div>
            </div>
            </c:forEach>
            <c:if test="${ sessionScope.sesmapdml == null }">
            	<h2 class="text-center aucun">Aucune Demande n'a été faite</h2>
            </c:if>
            
            <div class="container">
                <!-- Si la session de client n'est pas vide Faire -->
                <c:if test="${ sessionScope.sesmapdml != null }">
                <form action="<c:url value="/add_demande" />" method="POST">
                    <input class="acc-de" type="submit" value="Accepté">
                </form>
                <h2 class="text-center" ${ !form_add_dm.resultat == true ? 'style="color: green; margin-top: 10px;"' : 'style="color: red; margin-top: 10px;"' }>
                	<c:out value="${ form_add_dm.resultat }" />
                </h2>
                </c:if>
                <!-- Fin Si -->
            </div>
            <!-- les Demandes acceptés   -->
            <div class="container vg-acc">
                <h2 id="2" class="text-center">Les Demandes acceptés</h2>
                <c:forEach items="${ sesmapdml_db }" var="sdm">
                <div class="detail-vg">
                    <ul class="list-style info-list">
                        <li><c:out value="${ sdm.value.continent }" /></li>
                        <li><c:out value="${ sdm.value.pays }" /></li>
                        <li><c:out value="${ sdm.value.ville }" /></li>
                        <li title="details" class="icon">
                            <svg width="1em" data-class="<c:out value="${ sdm.value.ville }" />" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-compact-down selected" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M1.553 6.776a.5.5 0 0 1 .67-.223L8 9.44l5.776-2.888a.5.5 0 1 1 .448.894l-6 3a.5.5 0 0 1-.448 0l-6-3a.5.5 0 0 1-.223-.67z"/>
                            </svg>
                        </li>
                    </ul>
                </div>
                <div class="info-content">
                    <div class="<c:out value="${ sdm.value.ville }" />">
                        <ul class="list-style">
                            <li><span>Dtae :</span> <c:out value="${ sdm.value.date_v }" /> </li>
                            <li><span>Type de voyage :</span> <c:out value="${ sdm.value.type_v }" /> </li>
                            <li><span>Durée :</span> <c:out value="${ sdm.value.dure_v }" /> </li>
                            <li><span>Prix :</span> <c:out value="${ sdm.value.prix }" />$ </li>
                        </ul>
                    </div>
                </div>
                </c:forEach>
                <c:if test="${ sessionScope.sesmapdml == null }">
            		<h2 class="text-center aucun">Aucune Demande n'a été faite</h2>
            	</c:if>
            </div>
			</c:if>
			
			<c:if test="${ sessionScope.sesuser.admin == true }" >
			<div class="container vg-acc">
				<c:if test="${ ! empty sessionScope.MapDml_DB }" >
                <h2 id="2" class="text-center"> les demandes courante dans notre site :</h2>
                <c:forEach items="${ sessionScope.MapDml_DB }" var="dm">
                <div class="detail-vg">
                    <ul class="list-style info-list">
                        <li><c:out value="${ dm.value.continent }" /></li>
                        <li><c:out value="${ dm.value.pays }" /></li>
                        <li title="details" class="icon">
                            <svg width="1em" data-class="<c:out value="${ dm.value.ville }" />" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-compact-down selected" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M1.553 6.776a.5.5 0 0 1 .67-.223L8 9.44l5.776-2.888a.5.5 0 1 1 .448.894l-6 3a.5.5 0 0 1-.448 0l-6-3a.5.5 0 0 1-.223-.67z"/>
                            </svg>
                        </li>
                        <li title="Supprimer" class="del">
                        	<a href="<c:url value="/Supprimer" ><c:param name="id_dm" value="${ dm.key }"/></c:url>" >
                        		<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-dash-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path fill-rule="evenodd" d="M3.5 8a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.5-.5z"/>
                                </svg>
                        	</a>
                        </li>
                    </ul>
                </div>
                <div class="info-content">
                    <div class="<c:out value="${ dm.value.ville }" />">
                        <ul class="list-style">
                        	<li><span>Ville :</span> <c:out value="${ dm.value.ville }" /> </li>
                        	<li><span>Email :</span> <c:out value="${ dm.value.email }" /> </li>
                            <li><span>Dtae :</span> <c:out value="${ dm.value.date_v }" /> </li>
                            <li><span>Type de voyage :</span> <c:out value="${ dm.value.type_v }" /> </li>
                            <li><span>Durée :</span> <c:out value="${ dm.value.dure_v }" /> </li>
                            <li><span>Prix :</span> <c:out value="${ dm.value.prix }" />$ </li>
                        </ul>
                    </div>
                </div>
                </c:forEach>
                </c:if>
            </div>
            </c:if>
        </div>
        
        <!-- Ajouter un voyage par admin  -->
        <c:if test="${ sessionScope.sesuser.admin == true }" >
		<div class="tab-choix">
			<div class="container">
                <h2 class="text-center" id="3">Ajouter un voyege : </h2>
                <form action="<c:url value="/adminAjouterVg" />" method="POST">
                    <input class="inputF" type="text" name="continent" placeholder="Continent" value="${ vg.continent }" >
                    <input class="inputF" type="text" name="pays" placeholder="Pays" value="${ vg.pays }" >
                    <input class="inputF" type="text" name="ville" placeholder="Ville" value="${ vg.ville }" >
                    <input class="inputF" type="text" name="date_v" placeholder="Date" value="${ vg.date_v }" >
                    <input class="inputF" type="text" name="temp_v" placeholder="Temps" value="${ vg.temp_v }" >
                    <input class="inputF" type="text" name="dure_v" placeholder="Durée" value="${ vg.dure_v }" >
                    <select name="type_v" class="sel">
                        <option value="" class="tp_vg">Type de voyage</option>
                        <option value="Circuit accompagné">Circuit accompagné</option>
                        <option value="Touriste">Touriste</option>
                        <option value="Individuel">Individuel</option>
                        <option value="Famille">Famille</option>
                    </select>
                    <input class="inputF" type="text" name="prix" placeholder="Prix" value="${ vg.prix }" >
                    <input class="inputF" type="text" name="lien" placeholder="lien de l'image" value="${ vg.lien }" >
                    <textarea class="textAr" cols="60" name="description" rows="7" maxlength="700" placeholder="Écrire la description du voyage..." value="${ vg.description }" ></textarea>
                    <input type="submit" value="Ajouter" class="acc">
                    <div class="error">
                    	<c:if test="${ ! empty form_vg.erreurs['continent'] }">
                        <h4><c:out value=" erreur dans le continent : ${ form_vg.erreurs['continent'] }" /></h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['pays'] }">
                        <h4> <c:out value=" erreur dans le pays : ${ form_vg.erreurs['pays'] }" /> </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['ville'] }">
                        <h4> <c:out value=" erreur dans la ville : ${ form_vg.erreurs['ville'] }" /> </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['type_v'] }">
                        <h4> <c:out value=" erreur dans le type : ${ form_vg.erreurs['type_v'] }" /> </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['dure_v'] }">
                        <h4> <c:out value=" erreur dans la durée : ${ form_vg.erreurs['dure_v'] }" /> </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['prix'] }">
                        <h4> <c:out value=" erreur dans le prix : ${ form_vg.erreurs['prix'] }" /> </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['date_v'] }">
                        <h4> <c:out value=" erreur dans la date : ${ form_vg.erreurs['date_v'] }" /> </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['temp_v'] }">
                        <h4> <c:out value=" erreur dans le temps : ${ form_vg.erreurs['temp_v'] }" /> </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['description'] }">
                        <h4> <c:out value=" erreur dans la description : ${ form_vg.erreurs['description'] }" />  </h4>
                        </c:if>
                        <c:if test="${ ! empty form_vg.erreurs['lien'] }">
                        <h4> <c:out value=" erreur dans le lien de l'image : ${ form_vg.erreurs['lien'] }"/> </h4>
                        </c:if>
                        <h4 ${ empty form_vg.erreurs ? 'style="color: green;"' : 'style="color: red;"' } > 
                        	<c:out value="${ form_vg.resultat }" /> 
                        </h4>                        
                    </div>
                </form>
            </div>
		</div>
		</c:if>
		
        <!-- l'icon de Panier -->
        <c:import url="/WEB-INF/bot.jsp" />
        
        <c:if test="${ sessionScope.sesuser.admin == true }" >
        <c:if test="${ ! empty sessionScope.mesl }" >
        <div class="div-msg">
            <div class="remove-div-msg">
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-left-circle-fill arrow" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-7.646 2.646a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L6.207 7.5H11a.5.5 0 0 1 0 1H6.207l2.147 2.146z"/>
                </svg>
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-circle-fill remove" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.146-3.146a.5.5 0 0 0-.708-.708L8 7.293 4.854 4.146a.5.5 0 1 0-.708.708L7.293 8l-3.147 3.146a.5.5 0 0 0 .708.708L8 8.707l3.146 3.147a.5.5 0 0 0 .708-.708L8.707 8l3.147-3.146z"/>
                </svg>
            </div>
            <div class="msg-chat">
                <div class="client">
                	<c:forEach items="${ sessionScope.mesl }" var="mapMessages" varStatus="status">
                    <div class="div-person selected" data-class="<joda:format value="${ mapMessages.value.date }" pattern="HH'h'mm'min'ss" ></joda:format>">
                        <div class="person">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
                                <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                                <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
                            </svg>
                        </div>
                        <div class="name-person">
                            <a href="<c:url value="/adminSuppressionMessage"><c:param name="idMessage" value="${ mapMessages.key }" /></c:url>" title="Supprimer ce message">
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-dash-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path fill-rule="evenodd" d="M3.5 8a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.5-.5z"/>
                                </svg>
                            </a>
                            <h4>
                            	${ mapMessages.value.nomPrenom }
                            </h4>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div class="chat">
                	<c:forEach items="${ sessionScope.mesl }" var="mapMessages" varStatus="status">
                    <div class="chat-msg <joda:format value="${ mapMessages.value.date }" pattern="HH'h'mm'min'ss" ></joda:format>">
                    	<div class="titre">
                            <h4>Titre : <span class="text-center">${ mapMessages.value.titre }</span></h4>
                        </div>
                        <div class="content-msg-person">
                            <p>
                            	${ mapMessages.value.message }
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check-all" fill="currentColor" xmlns="http://www.w3.org/2000/svg" ${ mapMessages.value.vu ? 'style="color: blue;"' : 'style="color: #fff;"' }>
                                    <path fill-rule="evenodd" d="M8.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L2.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093L8.95 4.992a.252.252 0 0 1 .02-.022zm-.92 5.14l.92.92a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 1 0-1.091-1.028L9.477 9.417l-.485-.486-.943 1.179z"/>
                                </svg>
                            </p>
                            <span> <joda:format value="${ mapMessages.value.date }" pattern="dd/MM/yyyy 'à' HH'h'mm" ></joda:format> </span>
                        </div>
                    </div>
                    
                    <div class="msg-form <joda:format value="${ mapMessages.value.date }" pattern="HH'h'mm'min'ss" ></joda:format>">
                        <h4> ${ mapMessages.value.email } </h4>
                        <a href="mailto:${ mapMessages.value.email }"><i class="fa fa-reply" aria-hidden="true"></i></a>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        </c:if>
<!--         Tous les Voyages de notre site : -->
        <div class="list-vg">
            <div class="remove-list">
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-circle-fill remove" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.146-3.146a.5.5 0 0 0-.708-.708L8 7.293 4.854 4.146a.5.5 0 1 0-.708.708L7.293 8l-3.147 3.146a.5.5 0 0 0 .708.708L8 8.707l3.146 3.147a.5.5 0 0 0 .708-.708L8.707 8l3.147-3.146z"/>
                </svg>
            </div>
            <div class="elt-list-vg">
                <div>Continent</div>
                <div>Pays</div>
                <div>Villes</div>
                <div>Type</div>
                <div>Date</div>
                <div>Durée</div>
                <div>Prix</div>
                <div>Action</div>
            </div>
            <div class="info-list">
            	<c:forEach items="${ lvgp }" var="lvg">
            	<c:forEach items="${ all_v }" var="vg">
            	<c:if test="${ vg.pays == lvg.pays }" >
                <div class="content-list-vg">
                    <div> <c:out value="${ lvg.continent }" /> </div>
                    <div> <c:out value="${ vg.pays }" /> </div>
                    <div> <c:out value="${ vg.ville }" /> </div>
                    <div> <c:out value="${ vg.type_v }" /> </div>
                    <div> <c:out value="${ vg.date_v }" /> </div>
                    <div> <c:out value="${ vg.dure_v }" /> </div>
                    <div> <c:out value="${ vg.prix }" />$ </div>
                    <div>
                    	<a href="<c:url value="/Supprimer" > <c:param name="id_v" value="${ vg.id_v }" /> </c:url>">
                    		<img title="Supprimer" src="<c:url value="/images/supprimer.png"/>" alt="Supprimer le voyage">
                    	</a>
                    </div>
                </div>
                </c:if>
				</c:forEach>
				</c:forEach>
            </div>
        </div>
        </c:if>
        <c:if test="${ sessionScope.sesuser.admin == false }" >
        <div class="contact-pro">
            <h3 id="4">Contact : </h3>
            <form action="<c:url value="/contact" />" method="POST">
                <input type="text" name="titre" placeholder="Titre" class="titre">
                <div class="err-con err-titre">
                    <h4> <c:out value="${ form_mes.erreurs['titre'] }"/> </h4>
                </div>
                <textarea name="message" id="" cols="30" rows="10" placeholder="Message"></textarea>
                <div class="err-con">
                    <h4> <c:out value="${ form_mes.erreurs['message'] }"/> </h4>
                </div>
                <input type="submit" name="addmes" value="Envoyer" class="env">
                <div class="err-con">
                    <h4 ${ empty form_vg.erreurs ? 'style="color: green;"' : 'style="color: red;"' }>
                    	<c:out value="${ form_mes.resultat }"/>
                    </h4>
                </div>
            </form>
        </div>
        </c:if>
	</section>
	
	<!-- l'appelle de javascript -->
    <c:import url="/js/myScript.jsp" />
	
</body>
</html>
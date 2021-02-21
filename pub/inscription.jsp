<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>Agence-Inscription</title>
</head>
<body id="sign-body">
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
                <img src="<c:url value="/images/logo-2.png" />" />
            </div>
            <!-- les listes de barre navigation -->
            <ul class="ul list-style">
				
				<!-- l'importation du fichier navbar -->
                <c:import url="/WEB-INF/navbar.jsp" />
				
                <li><a href="<c:url value="/pub/login.jsp"/>">Login</a></li>
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
    
    <!-- la premier section au milieu contient le formulaire d'inscription -->
    <section class="sec2">
        <h3 class="text-center">Inscrivez-vous sur la plus grand page des voyages</h3>
        <div class="main-ins">
            <!-- logo de formulaire d'inscription -->
            <img src="<c:url value="/images/logo-2.png" />" />
            <!-- le formulaire d'inscription -->
            <form method="POST" action="<c:url value="/inscription" />">
                <div class="divinsc">
                	<div class="inputbox">
                    	<div class="in">
                    		<i class="fa fa-user" aria-hidden="true"></i>
                    		<input type="text" name="nom" placeholder="Nom" value="${ user.nom }">
                    	</div>
                    	<div class="vr">
                    	    <h4 class="err">${ form.erreurs['nom'] } </h4>
                    	</div>
                	</div>
                	<div class="inputbox">
                	    <div class="in">
                	    	<i class="fa fa-user" aria-hidden="true"></i>
                    		<input type="text" name="prenom" placeholder="Prenom" value="${ user.prenom }">
                	    </div>
                	    <div class="vr">
                			<h4 class="err">${ form.erreurs['prenom'] } </h4>
                		</div>
                	</div>
                </div>
                <div class="divinsc">
                	<div class="inputbox">
                		<div class="in">
                			<i class="fa fa-envelope-square"></i>
                  			<input type="email" name="email" placeholder="Email" value="${ user.email }">
                		</div>
                		<div class="vr">
                    	    <h4 class="err">${ form.erreurs['email'] } </h4>
                    	</div>
                	</div>
                	<div class="inputbox">
                		<div class="in">
                			<i class="fa fa-phone"></i>
                  			<input type="text" name="tel" placeholder="Phone" value="${ user.tel}">
                		</div>
                		<div class="vr">
                    	    <h4 class="err">${ form.erreurs['tel'] } </h4>
                    	</div>
                	</div>
                </div>
                <div class="divinsc">
                	<div class="inputbox">
                		<div class="in">
                			<i class="fa fa-unlock-alt"></i>
                    		<input type="password" name="mdp" placeholder="Mot de passe">
                		</div>
                		<div class="vr">
                    	    <h4 class="err">${ form.erreurs['mdp'] } </h4>
                    	</div>
                    </div>
                	<div class="inputbox">
                		<div class="in">
                			<i class="fa fa-unlock-alt"></i>
                    		<input type="password" name="confirmation" placeholder="Confirme Mot de passe">
                		</div>
                		<div class="vr">
                    	    <h4 class="err">${ form.erreurs['confirmation'] } </h4>
                    	</div>
                    </div>
                </div>
                <div class="divinsc">
                	<div class="inputbox">
                		<div class="in">
                			<i class="fa fa-flag"></i>
                  			<input type="text" name="pays" placeholder="Pays" value="${ user.pays}">
                		</div>
                		<div class="vr">
                    	    <h4 class="err">${ form.erreurs['pays'] } </h4> 
                    	</div>
                	</div>
                	<div class="inputbox">
                		<div class="in">
                			<i class="fa fa-birthday-cake"></i>
                  			<input type="text" name="age" placeholder="Age" value="${ user.age}">
                		</div>
                		<div class="vr">
                    	    <h4 class="err">${ form.erreurs['age'] } </h4> 
                    	</div>
                	</div>
                </div>
                <div class="err-res">
                	<h4 ${ empty form.erreurs ? 'style="color: green;"' : 'style="color: red;"' } >
                        <c:out value="${ form.resultat }"/>
                	</h4>
                </div>
                <input type="submit" name="SignUp" value="Inscription"><br>
            </form>
        </div>
        <div class="clearfix"></div>
    </section>
    <div class="mgtb40"></div>
    <!-- la deuxieme section au milieu contient certaines meilleur voyages -->
    <section class="team">
        <div class="content">
            <div class="rows">
                <h1 class="text-center">Les meilleur voyages</h1>
                <p class="par lead">
                    Notre agence admet les meilleurs employés pour choisir les meilleurs destinations avec les temps le plus approprié pour le type qui vienne avec 
                    beaucoupe de travaille c'est pour cela en a cette liste des voyages  les plus favorable .
                </p>
            </div>
            <div class="rows mgt50px">
                <div class="column float-left">
                    <div class="imgBox">
                        <img src="<c:url value="https://media-cdn.tripadvisor.com/media/photo-p/1b/21/a9/d8/bretonne2.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">Turquie<br><span>Ankara</span></h3>
                        <h5>prix : 399$<small>10/04/2021</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="Turquie" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
                <div class="column float-left">
                    <div class="imgBox">
                        <img src="<c:url value="https://www.whitecase.com/sites/default/files/images/sidebar/2019/03/saopaulo_desktop_800x1280.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">Brésil<br><span>São Paulo</span> </h3>
                        <h5>prix : 310$ <small>07/12/2020</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="Brésil" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
                <div class="column float-left">
                    <div class="imgBox">
                        <img src="<c:url value="https://i.imgur.com/2XGcyc2.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">Chine<br><span>Hong Kong</span></h3>
                        <h5>prix : 399$$ <small>01/01/2021</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="Chine" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
                <div class="column float-left">
                    <div class="imgBox">
                        <img src="<c:url value="https://lp-cms-production.imgix.net/2019-06/stock-photo-new-york-city-manhattan-100424161.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">États-Unis<br><span>New York</span> </h3>
                        <h5>prix : 500$ <small>18/11/2020</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="États-Unis" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
                <div class="column float-left">
                    <div class="imgBox">
                        <img src="<c:url value="https://images.squarespace-cdn.com/content/v1/52e10486e4b096ffec61978e/1513848413174-01TODTLZFI8B067EN65J/ke17ZwdGBToddI8pDm48kBOAEDvuhZ6jJw0vwk76EzYUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dnzQD80i1dS2uBoOnkpPxlDVBkS6Lr3TMl1uT-k1TgacMW9u6oXQZQicHHG1WEE6fg/rabat-ville-exterieur-dascia.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">Maroc<br><span>Rabat</span> </h3>
                        <h5>prix : 250$ <small>10/11/2020</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="Maroc" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
                <div class="column float-left">
                    <div class="imgBox">
                        <img src="<c:url value="https://i.pinimg.com/originals/7e/d1/5e/7ed15e451922f3c3be1d6854010fc077.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">Suisse<br><span>Landeron</span> </h3>
                        <h5>prix : 710$ <small>01/11/2020</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="Suisse" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
                <div class="column float-left ">
                    <div class="imgBox">
                        <img src="<c:url value="https://i.pinimg.com/originals/42/34/83/42348324d3ba3e0eb0a64822dfdf111d.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">Algérie<br><span>Oran</span> </h3>
                        <h5>prix : 400$ <small>15/12/2020</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="Algérie" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
                <div class="column float-left">
                    <div class="imgBox">
                        <img src="<c:url value="https://d3fmrrhqjo9at1.cloudfront.net/seo/thumbs_240x240/seo-consigne-place/storage-luggage-berlin-lustgarten.jpg"/>" />
                    </div>
                    <div class="details">
                        <h3 class="h3">allemagne<br><span>Berlin</span> </h3>
                        <h5>prix : 770$ <small>01/12/2020</small></h5>
                        <h6 class="text-center"><a href="<c:url value="/pub/destination.jsp"> <c:param name="v_pays" value="allemagne" /> </c:url>">Voir plus</a></h6>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </section>
    
    <!-- la partie en bas  -->
    <!-- l'importation du fichier footer -->
       <c:import url="/WEB-INF/footer.jsp" />
    
    <!-- l'appelle de javascript -->
    <c:import url="/js/myScript.jsp" />

</body>
</html>
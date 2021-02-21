<%@ page pageEncoding="UTF-8" %>
    
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
    <title>Agence-Login</title>
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
    <!-- barre de navigation -->
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
                <li><a href="<c:url value="/pub/inscription.jsp"/>">Inscription</a></li>
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
    <!-- Fin de la barre de navigation -->
    <!-- l'importation du fichier Slide -->
      <c:import url="/WEB-INF/Slide.jsp" />
    <section class="sec1-login">
        <div class="form">
            <form method="POST" action="<c:url value="/connexion"/>">
                <div class="circle text-center">
                    <img src="<c:url value="/images/login.png"/>" alt="Login">
                </div>
                <div class="input-box">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <input type="text" name="email" value="${ user.email }" placeholder="E-mail" />
                </div>
                <div class="err-h4">
                    <h4> <c:out value="${ form.erreurs['email'] }"/> </h4>
                </div>
                <div class="input-box">
                    <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                    <input type="password" name="mdp" placeholder="Mot de passe" />
                </div>
                <div class="err-h4">
                    <h4> <c:out value="${ form.erreurs['mdp'] }"/> </h4>
                </div>
                <div class="input-box">
                    <input class="text-center" type="submit" value="Connexion"/>
                </div>
                <div class="err-h4">
                    <h4 class="err-con">
                    	<c:out value="${ form.resultat_con }"/>                     
                    </h4>
                </div>
                <a href="<c:url value="/pub/inscription.jsp"/>">Don't have a count ?</a>
            </form>
        </div>
    </section>
    <!-- la partie en bas  -->
    <!-- l'importation du fichier footer -->
       <c:import url="/WEB-INF/footer.jsp" />
    
    <!-- l'appelle de javascript -->
    <c:import url="/js/myScript.jsp" />

</body>
</html>
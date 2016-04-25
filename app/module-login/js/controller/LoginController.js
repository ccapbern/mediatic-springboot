
// Récupération du module des catalogue pour y ajouter le controller
angular.module('ModuleLogin').controller('LoginController', ['$rootScope', '$routeParams', 'LoginService', function ($rootScope, $routeParams, LoginService) {
	var myCtrl = this;
	
	// Je définis l'attribut PAGE pas si il n'ai pas déjà défini
    $rootScope.page = $rootScope.page || {};
    // Je définis l'attribut TITRE de PAGE
    $rootScope.page.titre = "Page de login";
    
    myCtrl.login = function(){
    	var login = myCtrl.connexion.login;
    	var password = myCtrl.connexion.password;
    	LoginService.login(login, password);
    };
}]);
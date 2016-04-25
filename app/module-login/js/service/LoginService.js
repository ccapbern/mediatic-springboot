angular.module('ModuleLogin').service('LoginService', ['$http', '$rootScope', function ($http, $rootScope) {
	var self = this;
	
	var isConnected = true;
	var identifiant;
	
	self.login = function(login, password){
		var url = "http://10.34.10.140:8080/resource/connexion.login";
		identifiant = login;
		var promise = $http.post(url, {login:login, mdp:password}).then(function (response) {
            console.log("Connexion réussie");
            isConnected = true;
			return response.data;
        }, function (response){
        	console.log("Connexion refusée", response);
            isConnected = false;
        	return response.data;
        });
		return promise;
	};
	
	$rootScope.isConnected = function(){
		return isConnected;
	};
	
	self.getLogin = function(){
		return identifiant;
	};
	
	self.deconnexion = function(){
		isConnected = false;
		identifiant = "";
	}
}]);
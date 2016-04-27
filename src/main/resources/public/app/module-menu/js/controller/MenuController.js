angular.module('ModuleMenu').controller('MenuController', ['$location', '$rootScope', 'LoginService', function($location, $rootScope, LoginService) {
    var self = this;
    
    self.isMediaActif = function(){
        return  $rootScope.page!==undefined && $rootScope.page.code == "MEDIA";
    };
    
    self.isAdherentActif = function(){
        return  $rootScope.page!==undefined && $rootScope.page.code == "ADHERENT";
    };
    
    self.getLogin = function(){
    	return LoginService.getLogin();
    };
    
    self.deconnexion = function(){
    	LoginService.deconnexion();
    };
}]);

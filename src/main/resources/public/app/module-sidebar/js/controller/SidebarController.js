angular.module('ModuleSidebar').controller('SidebarController', ['$location', '$rootScope', 'MediaService', 'AdherentService', function($location, $rootScope, MediaService, AdherentService) {
    var self = this;
    
    
    self.isMediaActif = function(){
        return  $rootScope.page!==undefined && $rootScope.page.code == "MEDIA";
    };
    
    self.isAdherentActif = function(){
        return  $rootScope.page!==undefined && $rootScope.page.code == "ADHERENT";
    };
    
    self.submitMedia = function(){
        var filtres = {
            titre : self.title,
            auteur : self.author,
            type : self.type
        };
        MediaService.setFilters(filtres);
        self.open=false;
        return true;
    };
    
    self.submitAdherent = function(){
        var filtres = {
            nom : self.nom,
            prenom : self.prenom
        };
        AdherentService.setFilters(filtres);
        self.open=false;
        return true;
    };
    
    self.goMediaCreate = function(){
        console.log('enter here');
        return $location.path('/media/create');
    };
    
    self.goAdherentCreate = function(){
        return $location.path('/adherent/create');
    };
}]);
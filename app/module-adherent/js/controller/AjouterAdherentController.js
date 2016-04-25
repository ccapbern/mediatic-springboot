angular.module('ModuleAdherent').controller('AjouterAdherentController', ['$rootScope', '$location', 'AdherentService', function ($rootScope, $location, AdherentService) {
        var myCtrl = this;

        // Je défini l'attribut PAGE pas si il n'ai pas déjà défini
        $rootScope.page = $rootScope.page || {};
        // Je défini l'attribut TITRE de PAGE
        $rootScope.page.titre = "Ajouter un  adhérents";
        $rootScope.sidebar = false;

        myCtrl.adherent = undefined;

        myCtrl.addAdherent = function () {
            AdherentService.addAdherent(myCtrl.adherent);
            myCtrl.adherent = {};
            $location.path("/adherent");
        };
    }]);

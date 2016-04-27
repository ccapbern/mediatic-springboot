angular.module('ModuleMedia').controller('AjouterMediaController', ['$rootScope', '$location', 'MediaService', function ($rootScope, $location, MediaService) {
        var myCtrl = this;

        // Je défini l'attribut PAGE pas si il n'ai pas déjà défini
        $rootScope.page = $rootScope.page || {};
        // Je défini l'attribut TITRE de PAGE
        $rootScope.page.titre = "Ajouter un médias";
        $rootScope.sidebar = false;

        myCtrl.addMedia = function () {
            MediaService.addMedia(myCtrl.ajout);
            myCtrl.ajout = {};
            $location.path("/media");
        };
    }]);

angular.module('ModuleAdherent').controller('AdherentController', ['$rootScope', '$routeParams', 'AdherentService', 'MediaService', function ($rootScope, $routeParams, AdherentService, MediaService) {
        var myCtrl = this;

        // Je défini l'attribut PAGE pas si il n'ai pas déjà défini
        $rootScope.page = $rootScope.page || {};
        // Je défini l'attribut TITRE de PAGE
        $rootScope.page.titre = "Voir un adhérents";
        $rootScope.sidebar = false;

        myCtrl.adherent = undefined;

        myCtrl.pageEmprunt = 0;
        myCtrl.empruntsTotaux = [];
        myCtrl.emprunts;
        var nbPageEmprunt = 0;
        var nbEmpruntsParPage = 10;
        
        var id = $routeParams.adherent_id;

        AdherentService.getAdherent(id).then(function (response) {
            myCtrl.adherent = response;
            if(myCtrl.adherent.emprunt != undefined)
            	myCtrl.empruntsTotaux = myCtrl.adherent.emprunt;
            
            // Calcul du nombre de pages d'emprunts pour la pagination du tableau des emprunts d'un média 
            nbPageEmprunt = Math.ceil(myCtrl.empruntsTotaux.length / nbEmpruntsParPage);
            myCtrl.pagesEmpruntPossibles = [];
            for (var i = 0; i < nbPageEmprunt; i ++){
         	   myCtrl.pagesEmpruntPossibles.push(i);
            }
            myCtrl.emprunts = [];
            for (var i = myCtrl.pageEmprunt * nbEmpruntsParPage; i < myCtrl.pageEmprunt + nbEmpruntsParPage && i < myCtrl.empruntsTotaux.length; i++){
         	   myCtrl.emprunts.push(myCtrl.empruntsTotaux[i]);
            }
        }, function () {
            myCtrl.adherent = -1;
        });
        
        myCtrl.changePageEmprunt = function (pageEmprunt) {
            if (pageEmprunt >= 0 && pageEmprunt < nbPageEmprunt) {
                myCtrl.pageEmprunt = pageEmprunt;
                
                myCtrl.emprunts = [];
                for (var i = myCtrl.pageEmprunt * nbEmpruntsParPage; i < myCtrl.pageEmprunt + nbEmpruntsParPage && i < myCtrl.empruntsTotaux.length; i++){
                	myCtrl.emprunts.push(myCtrl.empruntsTotaux[i]);
                }
            }
        };
        
        myCtrl.mediasDisponibles = undefined;

        MediaService.getMediasDisponibles().then(function (response) {
            myCtrl.mediasDisponibles = response;
        }, function () {
            myCtrl.mediasDisponibles = -1;
        });
        
        myCtrl.addEmprunteur = function(){
        	AdherentService.addEmprunteur(myCtrl.ajout.selected, id);
        	myCtrl.ajout = {};
        };
        
        myCtrl.editAdherent = function() {
        	AdherentService.editAdherent(myCtrl.adherent);
        }
    }]);

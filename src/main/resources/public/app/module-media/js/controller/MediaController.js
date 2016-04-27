
// Récupération du module des catalogue pour y ajouter le controller
angular.module('ModuleMedia').controller('MediaController', ['$rootScope', '$routeParams', 'MediaService', 'AdherentService', function ($rootScope, $routeParams, MediaService, AdherentService) {
        var myCtrl = this;

        // Je définis l'attribut PAGE pas si il n'ai pas déjà défini
        $rootScope.page = $rootScope.page || {};
        // Je définis l'attribut TITRE de PAGE
        $rootScope.page.titre = "Voir un médias";
        $rootScope.sidebar = false;

        myCtrl.media = undefined;

        myCtrl.pageEmprunt = 0;
        myCtrl.emprunteursTotaux = [];
        myCtrl.emprunteurs;
        var nbPageEmprunt = 0;
        var nbEmpruntsParPage = 10;
        
        // Récuparation de l'identifiant qui est le paramètre 'idLivre' de la route
        var id = $routeParams.media_id;

        
        MediaService.getMedia(id).then(function (media) {
            myCtrl.media = media;
            if (myCtrl.media.emprunteurs != undefined)
            	myCtrl.emprunteursTotaux = myCtrl.media.emprunteurs;
            
           // Calcul du nombre de pages d'emprunts pour la pagination du tableau des emprunts d'un média 
           nbPageEmprunt = Math.ceil(myCtrl.emprunteursTotaux.length / nbEmpruntsParPage);
           myCtrl.pagesEmpruntPossibles = [];
           for (var i = 0; i < nbPageEmprunt; i ++){
        	   myCtrl.pagesEmpruntPossibles.push(i);
           }
           myCtrl.emprunteurs = [];
           for (var i = myCtrl.pageEmprunt * nbEmpruntsParPage; i < myCtrl.pageEmprunt + nbEmpruntsParPage && i < myCtrl.emprunteursTotaux.length; i++){
        	   myCtrl.emprunteurs.push(myCtrl.emprunteursTotaux[i]);
           }
            
        }, function () {
            // En cas d'erreur
            myCtrl.media = -1;
        });
        
        
        myCtrl.changePageEmprunt = function (pageEmprunt) {
            if (pageEmprunt >= 0 && pageEmprunt < nbPageEmprunt) {
                myCtrl.pageEmprunt = pageEmprunt;
                
                myCtrl.emprunteurs = [];
                for (var i = myCtrl.pageEmprunt * nbEmpruntsParPage; i < myCtrl.pageEmprunt + nbEmpruntsParPage && i < myCtrl.emprunteursTotaux.length; i++){
                	myCtrl.emprunteurs.push(myCtrl.emprunteursTotaux[i]);
                }
            }
        };
        
        myCtrl.adherentsActifs = undefined;

        AdherentService.getAdherentsActifs().then(function (response) {
            myCtrl.adherentsActifs = response;
        }, function () {
            myCtrl.adherentsActifs = -1;
        });
        
        myCtrl.addEmprunteur = function(){
        	MediaService.addEmprunteur(id, myCtrl.ajout.selected);
        	myCtrl.ajout = {};
        };
        
        myCtrl.editMedia = function() {
        	MediaService.editMedia(myCtrl.media);
        }
    }]);
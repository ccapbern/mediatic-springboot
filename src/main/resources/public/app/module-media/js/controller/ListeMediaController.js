angular.module('ModuleMedia').controller('ListeMediaController', ['$rootScope', '$location', 'MediaService', function ($rootScope, $location, MediaService) {
        var myCtrl = this;

        // Je défini l'attribut PAGE pas si il n'ai pas déjà défini
        $rootScope.page = $rootScope.page || {};
        // Je défini l'attribut TITRE de PAGE
        $rootScope.page.titre = "Liste des médias";
        $rootScope.page.code = "MEDIA";
        $rootScope.sidebar = true;

        myCtrl.page = 0;
        var nbPage = 0;

        var medias = undefined;
        myCtrl.getMedias = function () {
            if (MediaService.updated) {
            	//medias = undefined;
                updateMedias(myCtrl.page);
            } else {
                return medias;
            }
        };

        myCtrl.changePage = function (page) {
            if (page >= 0 && page < nbPage) {
                myCtrl.page = page;
                MediaService.updated = true;
            }
        };

        myCtrl.showMedia = function (id) {
            $location.path('/media/' + id);
        };
        
        myCtrl.triMedia = function(tri) {
        	var filtres = {
        			tri: tri
        	};
        	
        	MediaService.setFilters(filtres);
        };
        
        var updateMedias = function(page) {
        	MediaService.getMedias(page).then(function (response) {
                medias = response;
            }, function () {
                medias = -1;
            });
            MediaService.getNbPageMedias().then(function (response) {
                nbPage = response;
                myCtrl.pagesPossibles = [];
                for (var i = 0; i < nbPage; i++) {
                    myCtrl.pagesPossibles.push(i);
                }
            }, function () {
                medias = -1;
            });
        }
        
        updateMedias(myCtrl.page);

    }]);
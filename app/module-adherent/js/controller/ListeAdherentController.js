angular.module('ModuleAdherent').controller('ListeAdherentController', ['$rootScope', '$location', 'AdherentService', function ($rootScope, $location, AdherentService) {
        var myCtrl = this;

        // Je défini l'attribut PAGE pas si il n'ai pas déjà défini
        $rootScope.page = $rootScope.page || {};
        // Je défini l'attribut TITRE de PAGE
        $rootScope.page.titre = "Liste des adhérents";
        $rootScope.page.code = "ADHERENT";
        $rootScope.sidebar = true;

        myCtrl.page = 0;
        var nbPage = 0;

        var adherents = undefined;
        myCtrl.getAdherents = function () {
            if (AdherentService.updated) {
            	// adherents = undefined;
                updateAdherents(myCtrl.page);
            } else {
                return adherents;
            }
        };
        
        myCtrl.triAdherents = function (tri) {
                var filtres = {
                    tri : tri
                };
                AdherentService.setFilters(filtres);
        };
        
        myCtrl.changePage = function (page) {
        	if (page >= 0 && page < nbPage) {
        		myCtrl.page = page;
        		AdherentService.updated = true;
        	}
        };

        myCtrl.showAdherent = function (id) {
            $location.path('/adherent/' + id);
        };
        
        var updateAdherents = function(page) {
        	AdherentService.getAdherents(page).then(function (response) {
                adherents = response;
            }, function () {
                adherents = -1;
            });
            AdherentService.getNbPageAdherent().then(function (response) {
            	nbPage = response;
            	myCtrl.pagesPossibles = [];
            	for (var i = 0; i < nbPage; i++) {
            		myCtrl.pagesPossibles.push(i);
            	}
        	}, function () {
        		adherents = -1;
        	});
        }
        
        updateAdherents(myCtrl.page);

    }]);

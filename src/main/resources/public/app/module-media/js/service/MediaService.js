angular.module('ModuleMedia').service('MediaService', ['$http', function ($http) {
        var self = this;
        
        self.updated = false;
        
        var filtres = {};
        self.setFilters = function(p){
           filtres = p;
           self.updated = true;
        }

        self.getMedias = function (page) {
            self.updated = false;
            //filtres.page = page;
            var url = "http://localhost:8080/api/medias" /*/page/" + page*/;
            console.log("getMedias(page)" + page);
            var promise = $http.get(url).then(function (response) {
            	console.log(response.data);
            	return response.data;
            });
            
            return promise;
        };
        
        self.getNbPageMedias = function () {
            var url = "http://localhost:8080/api/medias/nbpages";
            console.log("getNbPageMedias");
            var promise = $http.get(url).then(function (response) {
            	console.log(response.data);
                return response.data.pages;
            });
            
            return promise;
        };
        
        self.getMediasDisponibles = function () {
            var url = "http://localhost:8080/api/medias";
            var promise = $http.get(url).then(function (response) {
                var mediasDisponibles = [];
            	for (var i = 0; i < response.data.length; i++){
            		var media = response.data[i];
            		if (media.emprunteur == null || media.emprunteur == []){
            			mediasDisponibles.push(media);
            		}
            	}
            	return mediasDisponibles;
            });

            return promise;
        };

        self.getMedia = function (id) {
            var url = "http://localhost:8080/api/medias/" + id;
            var promise = $http.get(url).then(function (response) {
            	console.log(response.data);
                return response.data;
            });

            return promise;
        };

        self.addMedia = function (media) {
            var url = "http://localhost:8080/api/medias";
            console.log("addMedia");
            var promise = $http.post(url, media).then(function (response) {
            	console.log(response.data);
                return response.data;
            });
            
            return promise;
        };
        
        self.addEmprunteur = function (idMedia, emprunteur) {
            var url = "http://localhost:8080/api/medias";
            var promise = $http.post(url, emprunteur.id, idMedia).then(function (response) {
            	return response.data;
            },function(response){
            	return response.data;
            });

            return promise;
        };
        
        self.editMedia = function(media) {
        	var url = "http://localhost:8080/api/medias";
        	var promise = $http.post(url, media).then(function (response) {
        		return response.data;
        	}, function(response) {
        		return response.data;
        	});
        };
    }]);
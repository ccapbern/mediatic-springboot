angular.module('ModuleAdherent').service('AdherentService', ['$http', function ($http) {
    var self = this;

    self.updated = false;

    var filtres = {};
    self.setFilters = function (p) {
        filtres = p;
        self.updated = true;
    };

    self.getAdherents = function (page) {
        self.updated = false;
        filtres.page = page;
        var url = "http://localhost:8080/api/members";
        var promise = $http.get(url, {params: filtres}).then(function (response) {
            return response.data;
        });

        return promise;
    };

    self.getNbPageAdherent = function () {
        var url = "http://localhost:8080/api/members/size";
        var promise = $http.get(url, {params: filtres}).then(function (response) {
            return response.data.pages;
        });

        return promise;
    };

    self.getAdherentsActifs = function () {
        var url = "http://localhost:8080/members";
        var promise = $http.get(url).then(function (response) {
            var adherentsActifs = [];
            for (var i = 0; i < response.data.length; i++) {
                var adherent = response.data[i];
                if (adherent.cotisation_correcte) {
                    adherentsActifs.push(adherent);
                }
            }
            return adherentsActifs;
        });

        return promise;
    };

    self.getAdherent = function (id) {
        var url = "http://localhost:8080/api/members/" + id;
        var promise = $http.get(url).then(function (response) {
            var data = response.data;

            data.dob = new Date(data.dob);

            data.subscription_date = data.subscription_date != undefined ? new Date(data.subscription_date) : null;
            data.subscription_end = data.subscription_end != undefined ? data.subscription_end : null;

            return data;
        });

        return promise;
    };

    self.addAdherent = function (adherent) {
        var url = "http://localhost:8080/api/members";
        var promise = $http.post(url, adherent).then(function (response) {
            return response.data;
        });

        return promise;
    };

    self.addEmprunteur = function (media, idEmprunteur) {
        var url = "http://10.34.10.140:8080/resource/emprunt.ajout";
        var promise = $http.post(url, idEmprunteur, media.id).then(function (response) {
            return response.data;
        }, function (response) {
            return response.data;
        });

        return promise;
    };

    self.editAdherent = function editAdherent(adherent) {
        var url = "http://localhost:8080/api/members";
        var promise = $http.post(url, adherent).then(function (response) {
            return response.data;
        }, function (response) {
            return response.data;
        });
    };
}]);
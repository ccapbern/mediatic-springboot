angular.module('ModuleLogin').service('LoginService', ['$http', '$rootScope', '$cookieStore', function ($http, $rootScope, $cookieStore) {
    var self = this;

    var isConnected = false;
    var identifiant;

    self.login = function (login, password) {
        var self = this;
        var key = btoa(login + ":" + password);

        var url = "http://localhost:8080/api/credential";

        var header = {Authorization: 'Basic ' + key};
        var config = {headers: header};

        var promise = $http.get(url, config).then(function(response) {
            if (response.status === 200) {
                $rootScope.globals = {
                    currentUser: {
                        login: login,
                        key: key
                    }
                };

                self.setCredential(key);
                identifiant = login;

                console.log($rootScope.globals);

                return response.data;
            }
        }, function(response) {
            return response.data;
        });
    };

    self.setCredential = function (key) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + key;
        $cookieStore.put('globals', $rootScope.globals);
        isConnected = true;
    };

    self.clearCredential = function() {
        $rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic ';
    };

    $rootScope.isConnected = function () {
        return $rootScope.globals.currentUser;
    };

    self.getLogin = function () {
        return identifiant;
    };

    self.deconnexion = function () {
        this.clearCredential();
        isConnected = false;
        identifiant = "";
    }
}]);
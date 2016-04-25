
// CrÃ©ation du module app avec les dÃ©pendances :
//  - ng-route : pour gÃ©rer des URL diffÃ©rentes

angular.module('App', ['ngRoute', 'ModuleMedia', 'ModuleMenu', 'ModuleAdherent', 'ModuleSidebar', 'ModuleLogin']);

// Configuration du module app
// => Injection du Provider du service $route afin de le configurer.
angular.module('App').config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({
        redirectTo: '/media'
    });
}]);

angular.module('App').run(['$http', function ($http) {
    $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
}]);
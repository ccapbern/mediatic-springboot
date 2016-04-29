angular.module('App', ['ngRoute', 'ngCookies', 'ModuleMedia', 'ModuleMenu', 'ModuleAdherent', 'ModuleSidebar', 'ModuleLogin']);

// Configuration du module app
// => Injection du Provider du service $route afin de le configurer.
angular.module('App').config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({
        redirectTo: '/media'
    });
}]);

angular.module('App').run(['$rootScope', '$location', '$cookieStore', '$http', function ($rootScope, $location, $cookieStore, $http) {
    $http.defaults.headers.post['Content-Type'] = 'application/json';

    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    console.log($rootScope.globals);
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.key;
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in
        if ($location.path() !== '/media' && !$rootScope.globals.currentUser) {
            $location.path('/media');
        }
    });
}]);
angular.module('ModuleMedia', ['ngRoute']);

angular.module('ModuleMedia').config(function ($routeProvider) {
    $routeProvider.when('/media', {
        templateUrl: './module-media/templates/media.html',
        controller: 'ListeMediaController',
        controllerAs: 'mediaCtrl'
    });
    
    $routeProvider.when('/media/create', {
        templateUrl: './module-media/templates/create_media.html',
        controller: 'AjouterMediaController',
        controllerAs: 'mediaCtrl'
    });
    
    $routeProvider.when('/media/:media_id', {
        templateUrl: './module-media/templates/display_media.html',
        controller: 'MediaController',
        controllerAs: 'mediaCtrl'
    });
});


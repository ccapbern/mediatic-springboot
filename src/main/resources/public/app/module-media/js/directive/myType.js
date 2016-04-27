angular.module('ModuleMedia').directive('myType', function(){
   return function(scope, element, attrs) {
       scope.$watch(attrs.myType, function(value) {
           element.text('');
           
           if (value === 'Livre') {
               element.append('<span class="glyphicon glyphicon-book" aria-hidden="true"></span>');
           }
           
           if (value === 'CD') {
               element.append('<span class="glyphicon glyphicon-music" aria-hidden="true"></span>');
           }
           
           if (value === 'DVD') {
               element.append('<span class="glyphicon glyphicon-film" aria-hidden="true"></span>');
           }
       });
   };
});
import { importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { routes } from './app/app.routing.module'; 
import { AppComponent } from './app/app.component';
import { provideHttpClient, withFetch } from '@angular/common/http';  // Importando provideHttpClient e withFetch

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(RouterModule.forRoot(routes)),  // Usando o arquivo de rotas corretamente
    provideHttpClient(withFetch())  // Habilitando o uso da API fetch
  ]
});

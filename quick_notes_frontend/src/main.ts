import { bootstrapApplication } from '@angular/platform-browser';
import { importProvidersFrom } from '@angular/core';
import { AppComponent } from './app/app.component';
import { HttpClientModule } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { FormsModule } from '@angular/forms';

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(HttpClientModule, FormsModule),
    provideRouter(routes)
  ]
})
.catch(err => console.error(err));

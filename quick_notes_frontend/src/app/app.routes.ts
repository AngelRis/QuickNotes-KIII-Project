import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NotesComponent } from './notes/notes.component';
import { AuthGuard } from './auth.guard';
import { NoteCreateComponent } from './note-create/note-create.component';
import { NoteEditComponent } from './note-edit/note-edit.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
  { path: 'register',component: RegisterComponent },
  {path: 'notes',component:NotesComponent,canActivate: [AuthGuard]},
  {path: 'note/create',component:NoteCreateComponent,canActivate: [AuthGuard]},
  {path: 'note/edit/:id',component:NoteEditComponent,canActivate: [AuthGuard]},
   { path: '', redirectTo: 'notes', pathMatch: 'full' }

];

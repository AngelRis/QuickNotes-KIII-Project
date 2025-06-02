import { Component } from '@angular/core';
import { Note } from '../models/note.model';
import { empty } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NoteService } from '../notes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-note-create',
  imports: [FormsModule, CommonModule],
  templateUrl: './note-create.component.html',
  styleUrl: './note-create.component.css'
})
export class NoteCreateComponent {
   note:Note={
     title: '', content: '', 
   };
   constructor(private noteServis:NoteService, private router: Router){

   }
   saveNote(){
    this.noteServis.createNote(this.note).subscribe({
      next: () => this.router.navigate(['/notes']),
    })
   }
}

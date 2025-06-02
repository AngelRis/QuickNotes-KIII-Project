import { Component } from '@angular/core';
import { Note } from '../models/note.model';
import { NoteService } from '../notes.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-notes',
  imports: [CommonModule,RouterModule ],
  templateUrl: './notes.component.html',
  styleUrl: './notes.component.css'
})
export class NotesComponent {
 notes: Note[] = [];
 constructor(private noteService: NoteService) {}

  ngOnInit(): void {

      this.noteService.getNotes().subscribe({
        next: list => this.notes = list
     
  });
    
  }
  deleteNote(id: number){
    this.noteService.deleteNote(id).subscribe({
      next:()=>{this.notes=this.notes.filter(note => note.id !== id);}
    })
  }

}

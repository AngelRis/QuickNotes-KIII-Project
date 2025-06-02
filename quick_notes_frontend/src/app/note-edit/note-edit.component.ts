import { Component } from '@angular/core';
import { NoteService } from '../notes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Note } from '../models/note.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-note-edit',
  imports: [FormsModule, CommonModule],
  templateUrl: './note-edit.component.html',
  styleUrl: './note-edit.component.css'
})
export class NoteEditComponent {
   note:Note={
    title: '', content: '', 
   };
   id!:number;
   constructor(private noteServis:NoteService, private router: Router,private route: ActivatedRoute){

   }
   ngOnInit(){
       this.id=+this.route.snapshot.paramMap.get('id')!
       this.noteServis.getNoteById(this.id).subscribe({
        next: n=>this.note=n
       })
   }
   updateNote(){
     this.noteServis.updateNote(this.id,this.note).subscribe({
      next:() => this.router.navigate(['/notes'])
     })
   }

}

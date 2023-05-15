import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';

@Component({
  selector: 'app-dijagnoza-dialog',
  templateUrl: './dijagnoza-dialog.component.html',
  styleUrls: ['./dijagnoza-dialog.component.css']
})
export class DijagnozaDialogComponent {

  public flagDijagDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public dijagnozaService: DijagnozaService,
    @Inject (MAT_DIALOG_DATA) public data: Dijagnoza,
    public dialogRef: MatDialogRef<DijagnozaDialogComponent>){}

  public add(): void {
      console.log("ID je " + this.data.id + this.data.naziv);
      this.dijagnozaService.addDijagnoza(this.data).subscribe(() => {
        this.snackBar.open('Uspesno dodata dijagnoza: ' + this.data.naziv, 'OK', {
          duration: 2500
        })
      }),
        (error: Error) => {
          console.log(error.name + ' ' + error.message)
          this.snackBar.open('Doslo je do greske prilikom dodavanja nove dijagnoze. ', 'Zatvori', {
            duration: 2500
          })
        };
  }

  public update(): void {
    this.dijagnozaService.updateDijagnoza(this.data).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena dijagnoza: ' + this.data.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene dijagnoze. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.dijagnozaService.deleteDijagnoza(this.data.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisana dijagnoza: ' + this.data.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja dijagnoze. ', 'Zatvori', {
          duration: 2500
        })
      };
}

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }


}

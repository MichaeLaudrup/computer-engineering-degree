
import { animate, keyframes, query, style, transition, trigger, group } from "@angular/animations";


export const fromModalToFeedbackMood = trigger('feeback-mood', [
    transition('* => true',

            group([
                query('.modal-component',animate('.7s .4s', style({ height: '60px'})), {optional:true}),
                query(':leave', animate( '.4s', style( {opacity: 0})),{optional:true}),
                query(':enter', [style({ opacity: 0}),  animate( '.7s 1100ms', style({ opacity: 1}))],{optional:true})]

    ))
])



export const enterAndLeaveModalAnimation = trigger('modal-animation', [
    transition(':enter', 
    query('.modal-component',[
        animate('.7s ease-in-out', keyframes([
            style({
              opacity: 0,
              transform: 'scale(0)',
              offset: 0
            }),
            style({
              transform: 'scale(1.2)',
              opacity: 1,
              offset: .8
            }),
            style({
              opacity: 1,
              transform: 'scale(1)',
              offset: 1
            })
          ]))
    ],{optional: true}), ),
    transition(':leave', 
    query('.modal-component',[
        animate('.7s ease-in-out', keyframes([
            style({
              opacity: 1,
              transform: 'scale(1)',
              offset: 0
            }),
            style({
              transform: 'scale(1.2)',
              opacity: 0.8,
              offset: .2
            }),
            style({
              opacity: 0,
              transform: 'scale(0)',
              offset: 1
            })
          ]))
    ],{optional: true}), )
])
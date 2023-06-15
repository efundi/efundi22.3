/**
 * @param {HTMLElement} el
 * @param {{isAsync?:boolean, releaseElement?: HTMLElement}} [config]
 */
export function mimicClick(el: HTMLElement, { isAsync, releaseElement }?: {
    isAsync?: boolean | undefined;
    releaseElement?: HTMLElement | undefined;
} | undefined): Promise<void>;

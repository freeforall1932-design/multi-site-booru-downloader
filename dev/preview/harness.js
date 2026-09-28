// Preview harness controls. The iframes above run the real UI modules in preview
// mode; this file only wires up navigation and the reset button.

const optionsFrame = document.getElementById('options-frame');
const popupFrame = document.getElementById('popup-frame');

document.getElementById('open-options')?.addEventListener('click', () => {
  window.open('../../dist/options/options.html', '_blank', 'noopener');
});

document.getElementById('open-popup')?.addEventListener('click', () => {
  window.open('../../dist/popup/popup.html?tab=https%3A%2F%2Fe621.net%2Fposts%2F1500000', '_blank', 'noopener');
});

document.getElementById('reset')?.addEventListener('click', () => {
  const confirmed = window.confirm('Clear preview storage and reload the harness?');
  if (!confirmed) return;
  Object.keys(localStorage)
    .filter((key) => key.startsWith('bsm-preview:'))
    .forEach((key) => localStorage.removeItem(key));
  optionsFrame?.contentWindow?.location.reload();
  popupFrame?.contentWindow?.location.reload();
  window.location.reload();
});
